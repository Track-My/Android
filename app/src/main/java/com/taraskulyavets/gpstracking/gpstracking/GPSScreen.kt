package com.taraskulyavets.gpstracking.gpstracking

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun GpsScreen(
    vm: GPSViewModel
) {
    LaunchedEffect("qwe"){
        vm.onLaunch()
    }

    val isGranted by vm.isGranted.collectAsState()
    val buttonText by vm.buttonName.collectAsState()

    if (isGranted) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Button(onClick = {vm.onStartStopClick()}) {
                Text(text = buttonText)
            }
        }
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Button(onClick = {
                vm.requestPermission()
            }) {
                Text(text = "Enable Location")
            }
        }
    }
}