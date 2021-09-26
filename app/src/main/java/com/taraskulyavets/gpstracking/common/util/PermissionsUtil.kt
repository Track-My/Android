package com.taraskulyavets.gpstracking.common.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class PermissionsUtil(private val context: Context) {
    private lateinit var multiplePermissionActivityResultLauncher: ActivityResultLauncher<Array<String>>

    fun checkPermission() : Boolean {
        return context.checkSelfPermission(
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
                && context.checkSelfPermission(
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }
}