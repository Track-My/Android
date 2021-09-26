package com.taraskulyavets.gpstracking.common.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity

class ChannelUtil(val context: Context) {
    fun createChanel() {
        val name = "GPS"
        val descriptionText = "Tracking"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val mChannel = NotificationChannel("777", name, importance)
        mChannel.description = descriptionText
        val notificationManager = context.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(mChannel)
    }
}