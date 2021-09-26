package com.taraskulyavets.gpstracking.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taraskulyavets.gpstracking.common.util.GPSPrefs
import com.taraskulyavets.gpstracking.login.model.Credentials
import com.taraskulyavets.gpstracking.login.model.Device
import com.taraskulyavets.gpstracking.login.model.LoginCredentials
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val repo: LoginRepository, private val prefs: GPSPrefs) : ViewModel() {
    val login = MutableStateFlow("email")
    val pass = MutableStateFlow("password")
    val isLogined = MutableStateFlow(false)

    fun changedLogin(text: String) {
        login.tryEmit(text)
    }

    fun changedPass(text: String) {
        pass.tryEmit(text)
    }

    fun loginClick() {
        val credentials = LoginCredentials(
            Credentials(
                email = login.value,
                password = pass.value //todo create sha-256
            ),
            Device(
                name = "${android.os.Build.MANUFACTURER} ${android.os.Build.MODEL}",
                type = "MOBILE",
                uuid = android.os.Build.ID
            )
        )

        viewModelScope.launch {
            try {
                val response = repo.login(credentials)
                prefs.user = response.user
                prefs.token = response.token

                isLogined.tryEmit(true)
            }catch (e: Exception) {e.printStackTrace(System.out)}
        }
    }

    fun setIsLogined(b: Boolean) {
        isLogined.tryEmit(b)
    }
}