package com.webtoon.batch.service

import com.webtoon.client.naver.NaverClient
import com.webtoon.domain.repository.ToonRepository
import org.springframework.stereotype.Service

@Service
class NaverToonCrawlService(
    private val toonRepository: ToonRepository,
    private val naverClient: NaverClient
) {
    fun naverToonCrawl() {
        val toonList = naverClient.crawlNaverData()
        toonRepository.saveAll(toonList)
    }
}