package com.widsons.core.data.model

class BaseResponse<T>(
    var message: String = "",
    var error: Boolean = false,
    var code: Int = 200,
    var details: T
)