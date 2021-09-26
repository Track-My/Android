package com.taraskulyavets.gpstracking.old

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class GPSViewModel: ViewModel() {

    private val _isGranted = MutableLiveData(false)
    val isGranted: LiveData<Boolean> = _isGranted

    fun onPermissionChange(isGranted: Boolean) {
        _isGranted.value = isGranted
    }

    private val _isRunning = MutableLiveData(false)
    val isRunning: LiveData<Boolean> = _isRunning

    fun onServiceStatusChange(isRunning: Boolean) {
        _isRunning.value = isRunning
    }
}
