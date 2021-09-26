package com.taraskulyavets.gpstracking.di

import android.content.Context
import android.content.SharedPreferences
import com.taraskulyavets.gpstracking.common.util.ChannelUtil
import com.taraskulyavets.gpstracking.common.util.GPSPrefs
import com.taraskulyavets.gpstracking.common.util.PermissionsUtil
import com.taraskulyavets.gpstracking.common.util.ServiceUtil
import com.taraskulyavets.gpstracking.gpstracking.GPSViewModel
import com.taraskulyavets.gpstracking.login.LoginRepository
import com.taraskulyavets.gpstracking.login.LoginViewModel
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { LoginViewModel(get(), get()) }
    viewModel { GPSViewModel(get(), get(), get()) }

    factory { LoginRepository(get()) }
    factory { ChannelUtil(androidContext()) }
    factory { PermissionsUtil(androidContext()) }
    factory { ServiceUtil(androidContext()) }

    single<SharedPreferences> {
        androidContext().getSharedPreferences(
            "MainPrefs",
            Context.MODE_PRIVATE
        )
    }
    single { GPSPrefs() }
    single {
        HttpClient(Android) {
            install(JsonFeature) {
                serializer = GsonSerializer()
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
    }
}