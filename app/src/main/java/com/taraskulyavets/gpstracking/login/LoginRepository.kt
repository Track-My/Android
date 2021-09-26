package com.taraskulyavets.gpstracking.login

import com.taraskulyavets.gpstracking.common.Config.API_URL
import com.taraskulyavets.gpstracking.common.Config.AUTHENTICATE_URL
import com.taraskulyavets.gpstracking.common.Config.SERVER_URL
import com.taraskulyavets.gpstracking.login.model.LoginCredentials
import com.taraskulyavets.gpstracking.login.model.LoginResponse
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class LoginRepository(private val client: HttpClient) {
    suspend fun login(credentials: LoginCredentials): LoginResponse {
        return client.post(AUTHENTICATE_URL) {
            headers {
                append(HttpHeaders.ContentType, "application/json")
            }
            body = credentials
        }
    }
}
