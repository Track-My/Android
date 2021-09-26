package com.taraskulyavets.gpstracking.login.model

data class LoginCredentials(val credentials: Credentials, val device: Device)

data class Device(val name: String, val type: String, val uuid: String)
data class Credentials(val email: String, val password: String)