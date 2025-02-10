package com.minutesock.remtoe

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

interface RemoteHttpClient {
    // todo-tyler create get method here
}

class KtorHttpClient: RemoteHttpClient {
    private val httpClient = createHttpClient()

    private fun createHttpClient(engine: HttpClientEngine = OkHttp.create()): HttpClient {
        return HttpClient(engine) {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                        coerceInputValues = true
                    }
                )
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 30_000
                connectTimeoutMillis = 30_000
            }
        }
    }
}