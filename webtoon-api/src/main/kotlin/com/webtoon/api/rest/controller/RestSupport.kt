package com.webtoon.api.rest.controller

import com.fasterxml.jackson.annotation.JsonAutoDetect
import io.swagger.annotations.ApiModel
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import java.nio.charset.StandardCharsets

val MEDIA_TYPE_APPLICATION_JSON_UTF8 = MediaType("application", "json", StandardCharsets.UTF_8)
const val MEDIA_TYPE_APPLICATION_JSON_UTF8_VALUE = "application/json;charset=UTF-8"

abstract class RestSupport {

    protected open fun <T> response(data: T): ResponseEntity<*> {
        return ResponseEntity
            .ok()
            .contentType(MEDIA_TYPE_APPLICATION_JSON_UTF8)
            .body(data)
    }

    protected open fun <T> dataResponse(data: T): ResponseEntity<*> {
        return ResponseEntity
            .ok()
            .contentType(MEDIA_TYPE_APPLICATION_JSON_UTF8)
            .body(DataResponse(data))
    }
}

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
@ApiModel("APIEnvelop")
data class DataResponse<T>(val data: T)