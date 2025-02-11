package com.minutesock.fetchhiring.core.di

import android.content.Context
import com.minutesock.remote.KtorHttpClient
import com.minutesock.remote.api.HiringApi
import com.minutesock.remote.api.KtorHiringApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface AppModule {
    val hiringApi: HiringApi
    val defaultDispatcher: CoroutineDispatcher
}

class ProductionAppModule(private val applicationContext: Context): AppModule {
    override val defaultDispatcher = Dispatchers.IO
    private val httpClient by lazy {
        KtorHttpClient()
    }

    override val hiringApi: HiringApi by lazy {
        KtorHiringApi(httpClient)
    }
}