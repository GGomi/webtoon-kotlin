package com.webtoon.client

import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono

abstract class RestClient(protected val client: WebClient) {
    protected inline fun <reified T : Any> WebClient.RequestHeadersSpec<*>.request(): Mono<T> {
        return this.retrieve().bodyToMono()
    }
}