package com.taraskulyavets.gpstracking.login.model

data class LoginResponse(val user: User, val device: Device, val token: String)
