package com.webtoon.client.config

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Positive

class ClientConfig {
    @field:NotBlank
    var url: String = ""

    @field:Positive
    var connectionTimeoutMillis: Int = 10_000

    @field:Positive
    var readTimeoutMillis: Long = 10_000

    @field:Positive
    var writeTimeoutMillis: Long = 10_000

    override fun toString(): String {
        return "ClientConfig(url='$url', connectionTimeoutMillis=$connectionTimeoutMillis, readTimeoutMillis=$readTimeoutMillis, writeTimeoutMillis=$writeTimeoutMillis)"
    }
}