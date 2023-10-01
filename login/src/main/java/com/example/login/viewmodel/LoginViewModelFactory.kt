package com.example.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory @Inject constructor() : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel() as T
    }
}