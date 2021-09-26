package com.taraskulyavets.gpstracking.gpstracking

import com.taraskulyavets.gpstracking.common.Config
import com.taraskulyavets.gpstracking.common.util.GPSPrefs
import com.taraskulyavets.gpstracking.gpstracking.model.MyLocation
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class LocationRepository(private val client: HttpClient, private val prefs: GPSPrefs) {
    suspend fun saveLocation(myLocation: MyLocation) {
        client.post<String>(Config.LOCATION_URL) {
            headers {
                append(HttpHeaders.ContentType, "application/json")
                append(HttpHeaders.Authorization, "Bearer ${prefs.token}")
            }
            body = myLocation
        }
    }
}