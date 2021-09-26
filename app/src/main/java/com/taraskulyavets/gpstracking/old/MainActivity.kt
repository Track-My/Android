package com.taraskulyavets.gpstracking.old

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
import com.taraskulyavets.gpstracking.gpstracking.GpsScreen


class MainActivity : AppCompatActivity() {




//    private val viewModel = GPSViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//
//        multiplePermissionActivityResultLauncher = registerForActivityResult(
//            ActivityResultContracts.RequestMultiplePermissions()
//        )
//        { isGranted ->
//            if (!isGranted.containsValue(false)) {
//                viewModel.onPermissionChange(true)
//            } else {
//                viewModel.onPermissionChange(false)
//            }
//        }
//
//        if (
//            true
//        ) {
//            multiplePermissionActivityResultLauncher.launch(PERMISSIONS)
//        } else {
//            viewModel.onPermissionChange(true)
//        }
//
//        viewModel.onServiceStatusChange(isServiceRunning(GpsService::class.java))
//
////        setContent {
////            GpsScreen(viewModel, ::startLocationTracking, ::stopLocationTracking) {
////                multiplePermissionActivityResultLauncher.launch(PERMISSIONS)
////            }
////        }
    }



   /*
   private fun startLocationTracking() {
        startService(Intent(this, GpsService::class.java))
        viewModel.onServiceStatusChange(isServiceRunning(GpsService::class.java))
    }

    private fun stopLocationTracking() {
        stopService(Intent(this, GpsService::class.java))
        viewModel.onServiceStatusChange(isServiceRunning(GpsService::class.java))
    }
    */
}