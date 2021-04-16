package com.webtoon.batch.scheduler

import com.webtoon.batch.service.DaumToonCrawlService
import com.webtoon.batch.service.NaverToonCrawlService
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.Scheduled

@Configuration
class WebtoonCrawlerScheduler(
    private val naverToonCrawl: NaverToonCrawlService,
    private val daumToonCrawlService: DaumToonCrawlService
) {

    @Scheduled(cron = "* 1 0 * * *")
    fun crawlToons() {
        naverToonCrawl.naverToonCrawl()
        daumToonCrawlService.daumToonCrawl()
    }
}