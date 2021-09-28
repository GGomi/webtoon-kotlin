package com.webtoon.client.naver

import com.webtoon.client.naver.constant.NaverConstant.NAVER_WEBTOON_LIST_TAG
import com.webtoon.domain.entity.Toon
import com.webtoon.domain.entity.enum.ToonProvider
import com.webtoon.domain.exception.WebtoonException
import com.webtoon.domain.exception.ErrorCode
import org.jsoup.Jsoup
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import java.util.regex.Pattern

@Configuration
class NaverClient(
    @Value("\${client.naver.url}") val naverUrl: String
) {

    private val log = LoggerFactory.getLogger(this::class.java)

    private val dayList = listOf("MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN")

    private val map: Map<String, Int> = mapOf(
        dayList[0] to 1,
        dayList[1] to 2,
        dayList[2] to 4,
        dayList[3] to 8,
        dayList[4] to 16,
        dayList[5] to 32,
        dayList[6] to 64
    )

    fun crawlNaverData(): List<Toon> {

        var flag = true
        val provider = ToonProvider.NAVER
        // 크롤링한 데이터를 담을 List
        val info: MutableList<Toon> = mutableListOf()

        // NAVER 요일별 웹툰 리스트 크롤링
        val doc = Jsoup.connect(naverUrl).get()
        val contents = doc.select(NAVER_WEBTOON_LIST_TAG)

        contents.map {
            val dayInfo = it.className().toUpperCase()
            val thumbs = it.nextElementSibling().select(".thumb")
            for (i in thumbs) {
                /*
                 * code         =>      웹툰 고유코드
                 * provider     =>      웹툰 플랫폼
                 * dayInfo      =>      웹툰 연재일
                 * href         =>      웹툰 링크
                 * src          =>      웹툰 썸네일 이미지 링크
                 * name         =>      웹툰 이름
                 */
                val aTag = i.selectFirst("a")
                val img = aTag.selectFirst("img")
                val href = aTag.attr("href")
                val src = img.attr("src")
                val name = img.attr("title")
                var code = ""
                var serial = map[dayInfo] ?: throw WebtoonException(ErrorCode.DATA_NOT_FOUND)
                val p = Pattern.compile("(\\d+\\d)")
                val m = p.matcher(href)

                // 정규식에 맞는 부분 찾기
                while (m.find()) {
                    code = m.group()
                }

                // 연재일이 하루가 아닌 웹툰들을 위해 OR연산을 통해 데이터 생성
                if (info.size != 0) {
                    for (t in info) {
                        if (t.toonCode == code) {
                            serial = t.serializeDay or serial
                            t.serializeDay = serial
                            flag = false
                            break
                        }
                    }
                }

                if (flag) {
                    info.add(
                        Toon(
                            toonCode = code,
                            imgSrc = src,
                            serializeDay = serial,
                            siteLink = href,
                            toonName = name,
                            toonProvider = provider
                        )
                    )
                } else {
                    flag = true
                }
            }
        }

        return info

    }
}