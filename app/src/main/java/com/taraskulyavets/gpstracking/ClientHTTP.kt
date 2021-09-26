package com.taraskulyavets.gpstracking

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*

object ClientHTTP {

    val client = HttpClient(Android) {
        install(JsonFeature) {
            serializer = GsonSerializer()
        }
    }
}