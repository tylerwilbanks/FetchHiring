package com.minutesock.remote

sealed class Endpoint(
    val path: String,
    val headers: Map<String, String> = emptyMap()
) {
    class Hiring : Endpoint(
        path = "https://fetch-hiring.s3.amazonaws.com/hiring.json"
    )
}

