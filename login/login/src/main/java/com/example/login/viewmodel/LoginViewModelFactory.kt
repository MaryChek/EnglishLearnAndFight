package com.example.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core_api.data.UserStorage
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory @Inject constructor(
    private val storage: UserStorage
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(storage) as T
    }
}