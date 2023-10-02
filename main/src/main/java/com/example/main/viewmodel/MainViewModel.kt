package com.example.main.viewmodel

import com.example.basescreen.viewmodels.BaseNavigateViewModel
import com.example.core_api.data.UserStorage
import com.example.main.navigation.FromMain

class MainViewModel(private val userStorage: UserStorage) : BaseNavigateViewModel<FromMain>() {

    fun init() {
        val name: String? = userStorage.getName()
        if (name == null) {
            handleNavigate(FromMain.GoTo.NewRootScreen.Login)
        } else {
            handleNavigate(FromMain.GoTo.NewRootScreen.Profile(name))
        }
    }
}