package com.example.login.viewmodel

import com.example.basescreen.viewmodels.BaseScreenViewModel
import com.example.core_api.data.UserStorage
import com.example.login.models.LoginScreenState
import com.example.login.navigation.FromLogin

class LoginViewModel(private val storage: UserStorage) :
    BaseScreenViewModel<LoginScreenState, FromLogin>(LoginScreenState("")) {

    private fun updateModel(newName: String) {
        model = LoginScreenState(newName)
        handleScreenState()
    }

    fun onContinueClick() {
        takeIf {
            model.isContinueEnable
        }?.let {
            storage.saveName(model.name)
            handleNavigate(FromLogin.GoTo.NewRootScreen.Profile(model.name))
        }
    }

    fun onNameChange(newName: String) =
        updateModel(newName)
}