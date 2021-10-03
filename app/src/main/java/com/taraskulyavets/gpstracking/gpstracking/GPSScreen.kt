package com.taraskulyavets.gpstracking.gpstracking

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.taraskulyavets.gpstracking.login.LoginViewModel

@Composable
fun GpsScreen(
    vm: GPSViewModel,
    login: LoginViewModel
) {
    LaunchedEffect("qwe"){
        vm.onLaunch()
    }

    val isGranted by vm.isGranted.collectAsState()
    val buttonText by vm.buttonName.collectAsState()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            if (isGranted) {
                Button(onClick = {vm.onStartStopClick()}) {
                    Text(text = buttonText)
                }
            } else {
                Button(onClick = {
                    vm.requestPermission()
                }) {
                    Text(text = "Enable Location")
                }
            }
            Spacer(Modifier.size(5.dp))
            Button(onClick = {
                vm.onStop()
                login.logout()
            }) {
                Text(text = "Logout")
            }
        }
    }
}