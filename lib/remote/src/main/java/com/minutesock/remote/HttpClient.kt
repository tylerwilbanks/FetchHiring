package com.minutesock.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class KtorHttpClient {
    val client = createHttpClient()

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

    suspend inline fun <reified T: Any> get(endpoint: Endpoint): ApiResponse<T> {
        return try {
            val response: HttpResponse = client.get {
                headers {
                    endpoint.headers.forEach { (key, value) ->
                        append(key, value)
                    }
                }
                url(endpoint.path)
            }
            if (response.status.isSuccess()) {
                val body = response.bodyAsText()
                return try {
                    ApiResponse.Success(Json.decodeFromString<T>(body))
                } catch (e: Exception) {
                    ApiResponse.Error("Response parsing error: ${e.message}", statusCode = 5_000)
                }
            } else {
                ApiResponse.Error(
                    message = response.status.description,
                    statusCode = response.status.value
                )
            }
        } catch (e: Exception) {
            ApiResponse.Error("Network error: ${e.message}", statusCode = 5_001)
        }
    }
}
