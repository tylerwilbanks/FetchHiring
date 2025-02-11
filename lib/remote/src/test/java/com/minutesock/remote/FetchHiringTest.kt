package com.minutesock.remote

import com.minutesock.remote.api.HiringApi
import com.minutesock.remote.api.KtorHiringApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

class FetchHiringTest {
    private val api: HiringApi = KtorHiringApi(
        client = KtorHttpClient()
    )

    @Test
    fun fetchHiringListItems() {
        runBlocking {
            val response = api.fetchHiringList()
            assert(response.isSuccessful())
            response.onSuccess { data ->
                assert(data.isNotEmpty())
            }
        }
    }
}