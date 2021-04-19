package com.webtoon.client.daum

import com.webtoon.client.RestClient
import com.webtoon.client.daum.constant.DaumConstant
import com.webtoon.client.daum.model.DaumRestTemplate
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.time.Instant

open class RestDaumClient(client: WebClient): DaumClient, RestClient(client) {

    override fun getDaumToonList(day: String): Mono<DaumRestTemplate> {
        val timestamp = Instant.now().epochSecond

        return client.get()
            .uri(day.toLowerCase() + "?timeStamp=$timestamp")
            .header("TIARA", "wSiyD.iDerurilteRXllei-OguUZZr5EkyiZIJWq1uZhINVhCsPLT_EPdFwcK29KzOW1xpdGqgudhvl.LSHuJVBvrnlmZFo7")
            .accept(MediaType.APPLICATION_JSON)
            .request()
    }

}