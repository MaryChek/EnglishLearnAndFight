package com.example.login.viewmodel

import android.util.Log
import com.example.basescreen.viewmodels.BaseScreenViewModel
import com.example.login.models.LoginScreenState

class LoginViewModel : BaseScreenViewModel<LoginScreenState>(LoginScreenState("")) {

    private fun updateModel(newName: String) {
        model = LoginScreenState(newName)
        handleScreenState()
    }

    fun onContinueClick() {
        Log.d("LoginViewModel", "onContinueClick: click")
    }

    fun onNameChange(newName: String) =
        updateModel(newName)
}