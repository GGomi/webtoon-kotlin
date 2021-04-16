package com.webtoon.api.rest.controller

import com.webtoon.api.service.ToonsService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Api(description = "웹툰 관련 API", tags = ["1. 웹툰"])
@RequestMapping("/api/v1/toons")
class ToonController(
    private val toonsService: ToonsService
): RestSupport() {
    @GetMapping
    @ApiOperation("웹툰 목록 가져오기")
    fun getToons(): ResponseEntity<*> {
        return dataResponse(toonsService.getToons())
    }
}