package com.example.login.viewmodel

import android.util.Log
import com.example.basescreen.viewmodels.BaseScreenViewModel
import com.example.core_api.data.UserStorage
import com.example.login.models.LoginScreenState

class LoginViewModel(private val storage: UserStorage) : BaseScreenViewModel<LoginScreenState>(LoginScreenState("")) {

    private fun updateModel(newName: String) {
        model = LoginScreenState(newName)
        handleScreenState()
    }

    fun onContinueClick() {
        storage.saveName(model.name)

        Log.d("LoginViewModel", "name is ${storage.getName()}")
    }

    fun onNameChange(newName: String) =
        updateModel(newName)
}