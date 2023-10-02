package com.example.main.viewmodel

import com.example.basescreen.viewmodels.BaseNavigateViewModel
import com.example.main.navigation.FromMain

class MainViewModel : BaseNavigateViewModel<FromMain>() {

    fun init() =
        handleNavigate(FromMain.GoTo.NewRootScreen.Login)
}