package com.taraskulyavets.gpstracking.common

import android.Manifest

object Config {
    const val SERVER_URL = "http://192.168.0.108:3001"
//    const val SERVER_URL = "http://vladar.tk:3001"
    const val API_URL = "$SERVER_URL/api"
    const val AUTHENTICATE_URL = "$API_URL/users/authenticate"
    const val LOCATION_URL = "$API_URL/locations"
    val PERMISSIONS = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
    )
}