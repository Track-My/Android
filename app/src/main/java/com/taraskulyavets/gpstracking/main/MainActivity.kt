package com.taraskulyavets.gpstracking.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.taraskulyavets.gpstracking.login.LoginViewModel
import com.taraskulyavets.gpstracking.login.LoginScreen
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    //TODO use koin compose
    private val loginViewModel by viewModel<LoginViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen(loginViewModel)
        }
    }
}



