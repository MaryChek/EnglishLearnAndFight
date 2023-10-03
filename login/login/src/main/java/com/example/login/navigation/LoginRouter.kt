package com.example.login.navigation

import com.example.basescreen.navigation.BaseRouter
import com.example.profile_api.ProfileScreen
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class LoginRouter @Inject constructor(private val router: Router) : BaseRouter<FromLogin.GoTo> {
    override fun goTo(destination: FromLogin.GoTo) {
        when (destination) {
            is FromLogin.GoTo.NewRootScreen.Profile ->
                router.newRootScreen(ProfileScreen.Profile(destination.profileName))
            is FromLogin.GoTo.Back ->
                router.exit()
        }
    }
}