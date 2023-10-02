package com.example.login.navigation

import com.example.basescreen.navigation.BaseRouter
import com.example.profile_api.ProfileScreen
import com.github.terrakok.cicerone.Router

class LoginRouter(private val router: Router) : BaseRouter<FromLogin.GoTo> {
    override fun goTo(destination: FromLogin.GoTo) {
        when (destination) {
            is FromLogin.GoTo.NewRootScreen.Profile ->
                router.navigateTo(ProfileScreen.Profile)
            is FromLogin.GoTo.Back ->
                router.exit()
        }
    }
}