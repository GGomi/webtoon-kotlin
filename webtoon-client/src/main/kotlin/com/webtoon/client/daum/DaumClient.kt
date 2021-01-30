package com.webtoon.client.daum

import com.webtoon.client.daum.model.DaumRestTemplate
import reactor.core.publisher.Mono

interface DaumClient {
    fun getDaumToonList(day: String ): Mono<DaumRestTemplate>
}