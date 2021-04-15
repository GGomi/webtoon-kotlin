package com.webtoon.api.controller

import com.webtoon.api.service.ToonsService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/toons")
class ToonController(
    private val toonsService: ToonsService
): RestSupport() {
    @GetMapping
    fun getToons(): ResponseEntity<*> {
        return dataResponse(toonsService.getToons())
    }
}