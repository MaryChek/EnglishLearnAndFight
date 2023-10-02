package com.example.main.viewmodel

import com.example.basescreen.viewmodels.BaseNavigateViewModel
import com.example.login.navigation.FromLogin

class MainViewModel : BaseNavigateViewModel<FromLogin>() {
    fun onViewCreated() {
        handleNavigate(FromLogin.GoTo.NewRootScreen.Profile)
    }
}