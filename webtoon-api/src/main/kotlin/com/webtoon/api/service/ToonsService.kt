package com.webtoon.api.service

import com.webtoon.api.dto.ToonItem
import com.webtoon.domain.entity.Toon
import com.webtoon.domain.repository.ToonRepository
import org.springframework.stereotype.Service


@Service
class ToonsService(
    private val toonRepository: ToonRepository
) {
    private val dayList = arrayOf("MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN")

    fun getToons(): MutableMap<String, MutableMap<String, MutableList<ToonItem>>> {
        val map = mutableMapOf<Int, String>()
        val serialPowNum = arrayOf(64, 32, 16, 8, 4, 2, 1)
        var pow = 1

        for (a in dayList) {
            map[pow] = a
            pow *= 2
        }

        val toons = toonRepository.findAll()
        val response = mutableMapOf<String, MutableMap<String, MutableList<ToonItem>>>()

        toons.distinctBy { it.toonProvider }.map { it.toonProvider }.map {
            response[it.name] = mutableMapOf()
        }

        toons.map {
            val providerList = response[it.toonProvider.name]
            val serialDays = makeSerialDays(it.serializeDay, serialPowNum, map)

            val toon = ToonItem(
                toonName = it.toonName,
                serializeDay = serialDays,
                toonHref = it.siteLink,
                toonImgSrc = it.imgSrc,
                toonProvider = it.toonProvider.name
            )

            for (tmp in serialDays) {
                var innerList = mutableListOf<ToonItem>()
                if (providerList!![tmp] != null) {
                    innerList = providerList[tmp]!!
                }
                innerList.add(toon)
                providerList[tmp] = innerList
            }

            if (providerList != null) {
                response[it.toonProvider.name] = providerList
            }
        }

        return response

    }

    private fun makeSerialDays(
        serial: Int,
        serialPowNum: Array<Int>,
        map: MutableMap<Int, String>
    ): MutableList<String> {
        var serialNum = serial
        val serialDays = mutableListOf<String>()

        for (num in serialPowNum) {
            if (serialNum >= num) {
                val key = map[num]
                serialDays.add(key!!)
                serialNum = (serialNum - num)
            }
        }
        return serialDays
    }
}