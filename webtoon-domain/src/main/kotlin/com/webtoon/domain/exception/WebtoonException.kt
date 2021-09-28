package com.webtoon.domain.exception

open class WebtoonException : RuntimeException {
    constructor() : super()
    constructor(message: String? = null) : super(message)
    constructor(exception: Throwable) : super(exception)
    constructor(errorCode: ErrorCode) : super(errorCode.message)
}