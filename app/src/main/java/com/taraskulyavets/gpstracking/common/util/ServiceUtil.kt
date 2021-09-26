package com.taraskulyavets.gpstracking.common.util

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.taraskulyavets.gpstracking.old.GpsService

class ServiceUtil(private val context: Context) {
    private companion object {
        val gpsClass = GpsService::class.java
    }

    fun startService() {
        context.startService(Intent(context, gpsClass))
    }

    fun stopService() {
        context.stopService(Intent(context, gpsClass))
    }

    @Suppress("DEPRECATION")
    fun isServiceRunning(): Boolean {
        return (context.getSystemService(AppCompatActivity.ACTIVITY_SERVICE) as ActivityManager)
            .getRunningServices(Integer.MAX_VALUE)
            .any { it.service.className == gpsClass.name }
    }
}