package com.taraskulyavets.gpstracking

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import android.app.ActivityManager
import android.content.pm.PackageManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.ActivityResultLauncher


class MainActivity : AppCompatActivity() {

    companion object {
        val PERMISSIONS = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        )
    }

    private lateinit var multiplePermissionActivityResultLauncher: ActivityResultLauncher<Array<String>>
    private val viewModel = GPSViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        multiplePermissionActivityResultLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        )
        { isGranted ->
            if (!isGranted.containsValue(false)) {
                viewModel.onPermissionChange(true)
            } else {
                viewModel.onPermissionChange(false)
            }
        }

        if (
            checkSelfPermission(
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && checkSelfPermission(
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            multiplePermissionActivityResultLauncher.launch(PERMISSIONS)
        } else {
            viewModel.onPermissionChange(true)
        }

        viewModel.onServiceStatusChange(isServiceRunning(GpsService::class.java))

        setContent {
            MainScreen(viewModel, ::startLocationTracking, ::stopLocationTracking) {
                multiplePermissionActivityResultLauncher.launch(PERMISSIONS)
            }
        }

        createChanel()
    }

    private fun createChanel() {
        val name = "GPS"
        val descriptionText = "Tracking"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val mChannel = NotificationChannel("777", name, importance)
        mChannel.description = descriptionText
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(mChannel)
    }

    @Suppress("DEPRECATION")
    fun <T> isServiceRunning(service: Class<T>): Boolean {
        return (getSystemService(ACTIVITY_SERVICE) as ActivityManager)
            .getRunningServices(Integer.MAX_VALUE)
            .any { it -> it.service.className == service.name }
    }

    private fun startLocationTracking() {
        startService(Intent(this, GpsService::class.java))
        viewModel.onServiceStatusChange(isServiceRunning(GpsService::class.java))
    }

    private fun stopLocationTracking() {
        stopService(Intent(this, GpsService::class.java))
        viewModel.onServiceStatusChange(isServiceRunning(GpsService::class.java))
    }
}