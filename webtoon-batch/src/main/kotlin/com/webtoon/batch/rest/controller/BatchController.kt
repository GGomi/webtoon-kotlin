package com.webtoon.batch.rest.controller

import com.webtoon.batch.service.DaumToonCrawlService
import com.webtoon.batch.service.NaverToonCrawlService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/batch")
class BatchController(
    private val naverToonCrawlService: NaverToonCrawlService,
    private val daumToonCrawlService: DaumToonCrawlService
) {
    @GetMapping("/naver")
    fun naverCrawling(): String {
        naverToonCrawlService.naverToonCrawl()
        return "OK"
    }

    @GetMapping("/daum")
    fun daumCrawling(): String {
        daumToonCrawlService.daumToonCrawl()
        return "OK"
    }
}