package com.example.apk_demo.api

data class HttpResModel<T>(
    var data: T,
    var code: Int,
    var message: String
)