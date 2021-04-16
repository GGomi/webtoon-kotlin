package com.webtoon.batch.config

import org.springframework.boot.info.BuildProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.http.ResponseEntity
import org.springframework.web.server.WebSession
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Configuration
@EnableSwagger2
class SwaggerConfig(private val buildProperties: BuildProperties) {

    @Bean
    fun docket(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .enable(true)
            .useDefaultResponseMessages(false)
            .ignoredParameterTypes(
                WebSession::class.java,
                HttpServletRequest::class.java,
                HttpServletResponse::class.java
            )
            .apiInfo(apiInfo())
            .genericModelSubstitutes(
                Optional::class.java,
                ResponseEntity::class.java
            )
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.webtoon.batch.rest.controller"))
            .paths(PathSelectors.regex("/api/.*"))
            .build()
    }

    private fun apiInfo() = ApiInfoBuilder()
        .title(buildProperties.name)
        .version(buildProperties.version)
//        .description()
        .build()
}