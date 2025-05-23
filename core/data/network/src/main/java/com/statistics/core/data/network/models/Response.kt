package com.statistics.core.data.network.models

open class Response<T>(
    var isSuccess: Boolean = false,
    var resultCode: StatusCode = StatusCode(0),
    var body: T? = null
)