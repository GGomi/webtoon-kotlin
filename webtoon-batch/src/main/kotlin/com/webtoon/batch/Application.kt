package com.webtoon.batch

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.context.ApplicationPidFileWriter
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.info.BuildProperties
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationListener
import org.springframework.core.env.Environment
import java.util.*
import javax.annotation.PostConstruct

@SpringBootApplication(
    scanBasePackages = [
        "com.webtoon.client",
        "com.webtoon.domain",
        "com.webtoon.batch"
    ]
)
class Application(
    private val environment: Environment,
    private val buildProperties: BuildProperties
) : ApplicationListener<ApplicationReadyEvent> {

    private val logger = LoggerFactory.getLogger(Application::class.java)

    @PostConstruct
    fun init() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"))
    }

    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        logger.info("{} applicationReady, profiles = {}", buildProperties.name, environment.activeProfiles.contentToString())
    }
}

fun main(args: Array<String>) {
    SpringApplicationBuilder(Application::class.java)
        .listeners(ApplicationPidFileWriter())
        .run(*args)
}