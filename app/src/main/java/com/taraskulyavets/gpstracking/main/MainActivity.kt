package com.taraskulyavets.gpstracking.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.taraskulyavets.gpstracking.common.Config.PERMISSIONS
import com.taraskulyavets.gpstracking.common.util.GPSPrefs
import com.taraskulyavets.gpstracking.gpstracking.GPSViewModel
import com.taraskulyavets.gpstracking.gpstracking.GpsScreen
import com.taraskulyavets.gpstracking.login.LoginViewModel
import com.taraskulyavets.gpstracking.login.LoginScreen
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    //TODO use koin compose
    private val loginViewModel by viewModel<LoginViewModel>()
    private val gpsViewModel by viewModel<GPSViewModel>()
    private val prefs by inject<GPSPrefs>()
    private val qwe = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    )
    { results ->
        gpsViewModel.onPermissionResult(!results.containsValue(false))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        gpsViewModel.requestPermission = ::requestPermissions

        loginViewModel.setIsLogined(prefs.token.isNotEmpty())

        setContent {
            val isLogined by loginViewModel.isLogined.collectAsState()
            if (isLogined) {
                GpsScreen(gpsViewModel, loginViewModel)
            } else {
                LoginScreen(loginViewModel)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        gpsViewModel.requestPermission = {}
    }

    private fun requestPermissions() {
        qwe.launch(PERMISSIONS)
    }
}



