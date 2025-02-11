package com.minutesock.remote.api

import com.minutesock.remote.ApiResponse
import com.minutesock.remote.Endpoint
import com.minutesock.remote.KtorHttpClient
import kotlinx.serialization.Serializable

@Serializable
class HiringDTO(
    val id: Long,
    val listId: Int,
    val name: String?
)

interface HiringApi {
    suspend fun fetchHiringList(): ApiResponse<List<HiringDTO>>
}

class KtorHiringApi(private val client: KtorHttpClient): HiringApi {
    override suspend fun fetchHiringList(): ApiResponse<List<HiringDTO>> {
        return client.get<List<HiringDTO>>(Endpoint.Hiring())
    }
}