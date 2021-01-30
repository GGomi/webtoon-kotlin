package com.webtoon.domain.exception

enum class ErrorCode(
    val status: Int,
    val code: String,
    val message: String
) {
    DATA_NOT_FOUND(404, "C001", "not_found"), INTERNAL_SERVER_ERROR(
        500,
        "C002",
        "Internal Server Error"
    ),
    INVALID_INPUT_VALUE(400, "U001", " Invalid Input Value"), INVALID_TYPE_VALUE(
        400,
        "U002",
        " Invalid Type Value"
    ),
    HANDLE_ACCESS_DENIED(403, "U003", "Access Denied");
}