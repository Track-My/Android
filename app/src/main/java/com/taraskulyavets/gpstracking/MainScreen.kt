package com.taraskulyavets.gpstracking

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun MainScreen(
    viewModel: GPSViewModel,
    startLocationTracking: () -> Unit,
    stopLocationTracking: () -> Unit,
    requestPermission: () -> Unit
) {

    val isGranted by viewModel.isGranted.observeAsState(false)
    val isRunning by viewModel.isRunning.observeAsState(false)

    if (isGranted) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Button(onClick = {
                if (isRunning) {
                    stopLocationTracking()
                } else {
                    startLocationTracking()
                }
            }) {
                if (isRunning) {
                    Text(text = "Stop Service")
                } else {
                    Text(text = "Start Service")
                }
            }
        }
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Button(onClick = {
                requestPermission()
            }) {
                Text(text = "Enable Location")

            }
        }
    }
}
