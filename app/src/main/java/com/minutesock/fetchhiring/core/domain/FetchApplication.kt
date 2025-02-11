package com.minutesock.fetchhiring.core.domain

import android.app.Application
import com.minutesock.fetchhiring.core.di.AppModule
import com.minutesock.fetchhiring.core.di.ProductionAppModule

class FetchApplication: Application() {
    companion object {
        lateinit var appModule: AppModule
    }

    override fun onCreate() {
        super.onCreate()
        appModule = ProductionAppModule(applicationContext)
    }
}