package com.webtoon.client.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.webtoon.client.daum.DaumClient
import com.webtoon.client.daum.RestDaumClient
import io.netty.channel.ChannelOption
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ClientHttpConnector
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.Connection
import reactor.netty.http.client.HttpClient
import reactor.netty.tcp.TcpClient
import java.util.concurrent.TimeUnit
import javax.validation.Valid

@Configuration
class ClientAutoConfigure {

    @Bean(CLIENT_OBJECT_MAPPER)
    @ConditionalOnMissingBean(name = [CLIENT_OBJECT_MAPPER])
    fun clientObjectMapper(): ObjectMapper {
        return jacksonObjectMapper()
            .enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .setSerializationInclusion(JsonInclude.Include.NON_ABSENT)
            .registerModule(JavaTimeModule())
    }

    @Bean
    @ConditionalOnProperty(prefix = "client.daum", name = ["url"])
    @ConfigurationProperties(prefix = "client.daum")
    fun daumConfig() = ClientConfig()

    @Bean
    @ConditionalOnBean(name = ["daumConfig"])
    @ConditionalOnMissingBean(DaumClient::class)
    fun restPMSClient(
        @Valid daumConfig: ClientConfig,
        @Qualifier(CLIENT_OBJECT_MAPPER) mapper: ObjectMapper
    ): DaumClient {
        return RestDaumClient(createClient(daumConfig))
    }

    private fun createClient(config: ClientConfig): WebClient {
        return WebClient.builder()
            .baseUrl(config.url)
            .clientConnector(createWebclientConnector(config))
            .build()
    }

    private fun createWebclientConnector(config: ClientConfig): ClientHttpConnector {
        return TcpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, config.connectionTimeoutMillis) // Connection Timeout
            .doOnConnected { connection: Connection ->
                connection
                    .addHandlerLast(ReadTimeoutHandler(config.readTimeoutMillis, TimeUnit.MILLISECONDS))
                    .addHandlerLast(WriteTimeoutHandler(config.writeTimeoutMillis, TimeUnit.MILLISECONDS))
            }
            .run { ReactorClientHttpConnector(HttpClient.from(this)) }
    }

    companion object {
        const val CLIENT_OBJECT_MAPPER = "clientObjectMapper"
    }
}