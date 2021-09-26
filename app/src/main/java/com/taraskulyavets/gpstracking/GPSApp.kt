package com.taraskulyavets.gpstracking

import android.app.Application
import com.taraskulyavets.gpstracking.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class GPSApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@GPSApp)
            modules(appModule)
        }
    }
}