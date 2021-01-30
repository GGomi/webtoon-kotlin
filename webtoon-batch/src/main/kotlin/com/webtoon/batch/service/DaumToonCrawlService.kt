package com.webtoon.batch.service

import com.webtoon.client.daum.DaumClient
import com.webtoon.domain.entity.Toon
import com.webtoon.domain.entity.enum.ToonProvider
import com.webtoon.domain.exception.BusinessException
import com.webtoon.domain.exception.ErrorCode
import com.webtoon.domain.repository.ToonRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class DaumToonCrawlService(
    private val toonRepository: ToonRepository,
    private val daumClient: DaumClient,
    @Value("\${client.daum.page}") val daumSiteLink: String
) {
    fun daumToonCrawl() {
        val day = listOf("MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN")
        val dayToSerialize: Map<String, Int> = mapOf(
            day[0] to 1,
            day[1] to 2,
            day[2] to 4,
            day[3] to 8,
            day[4] to 16,
            day[5] to 32,
            day[6] to 64
        )

        val toonsMap = mutableMapOf<String, Toon>()

        day.map {
            val daumClientResponse =
                daumClient.getDaumToonList(it).block() ?: throw BusinessException(ErrorCode.DATA_NOT_FOUND)

            if (daumClientResponse.data.isNotEmpty()) {
                daumClientResponse.data.map { datum ->
                    val toonId = datum.id.toString()

                    val toon = toonsMap[toonId] ?: Toon(
                        toonCode = toonId,
                        toonProvider = ToonProvider.DAUM,
                        toonName = datum.title,
                        siteLink = "$daumSiteLink${datum.nickname}",
                        serializeDay = dayToSerialize[it]!!,
                        imgSrc = if (datum.thumbnailImage != null) {
                            datum.thumbnailImage?.url ?: ""
                        } else {
                            datum.thumbnailImage2?.url ?: ""
                        }
                    )

                    if (toonsMap.containsKey(toonId)) {
                        toon.serializeDay = toon.serializeDay or dayToSerialize[it]!!
                        toonsMap[toonId] = toon
                    } else {
                        toonsMap[toonId] = toon
                    }
                }
            }
        }

        toonRepository.saveAll(toonsMap.values)

    }
}