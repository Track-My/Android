package com.taraskulyavets.gpstracking.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.vlad_taras.gpstracking.ui.theme.GPSTrackingTheme

@Composable
fun LoginScreen(vm: LoginViewModel) {
    val login by vm.login.collectAsState()
    val pass by vm.pass.collectAsState()
    GPSTrackingTheme {
        Column {
            TextField(value = login, onValueChange = {vm.changedLogin(it)})
            TextField(
                value = pass,
                onValueChange = { vm.changedPass(it) },
                visualTransformation = PasswordVisualTransformation('*'),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                )
            )
            Button(onClick = {vm.loginClick()}) {
                Text(text = "LOGIN")
            }
        }
    }
}