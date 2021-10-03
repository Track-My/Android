package com.taraskulyavets.gpstracking.gpstracking

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import com.taraskulyavets.gpstracking.GPSApp
import com.taraskulyavets.gpstracking.common.util.ChannelUtil
import com.taraskulyavets.gpstracking.common.util.GPSPrefs
import com.taraskulyavets.gpstracking.common.util.PermissionsUtil
import com.taraskulyavets.gpstracking.common.util.ServiceUtil
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class GPSViewModel(
    private val channelUtil: ChannelUtil,
    private val permissionsUtil: PermissionsUtil,
    private val serviceUtil: ServiceUtil
): ViewModel() {
    companion object {
        const val START = "Start service"
        const val STOP = "Stop service"
    }

    var requestPermission: ()->Unit = {}
    val buttonName = MutableStateFlow(START)

    private val _isGranted = MutableStateFlow(false)
    val isGranted: StateFlow<Boolean> = _isGranted.asStateFlow()

    private var isRunning = false
        set(value) {
            field = value
            if (isRunning) {
                buttonName.tryEmit(STOP)
            } else {
                buttonName.tryEmit(START)
            }
        }


    fun onStartStopClick() {
        isRunning = if (isRunning) {
            serviceUtil.stopService()
            false
        } else {
            serviceUtil.startService()
            true
        }
    }

    fun onStop() {
        serviceUtil.stopService()
        isRunning = false
    }

    fun onLaunch() {
        channelUtil.createChanel()

        _isGranted.tryEmit(permissionsUtil.checkPermission())
        isRunning = serviceUtil.isServiceRunning()
    }

    fun onPermissionResult(allAccepted: Boolean) {
        if (allAccepted) {
            _isGranted.tryEmit(true)
        }
    }
}
