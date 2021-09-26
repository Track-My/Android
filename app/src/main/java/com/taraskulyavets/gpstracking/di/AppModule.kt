package com.taraskulyavets.gpstracking.di

import com.taraskulyavets.gpstracking.common.helper.GPSPrefs
import com.taraskulyavets.gpstracking.login.LoginRepository
import com.taraskulyavets.gpstracking.login.LoginViewModel
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { LoginViewModel(get()) }

    factory { LoginRepository(get()) }


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