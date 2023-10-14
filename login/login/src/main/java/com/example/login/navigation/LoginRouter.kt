package com.example.login.navigation

import com.example.basescreen.navigation.BaseRouter
import com.example.profile_api.ProfileScreenMediator
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class LoginRouter @Inject constructor(
    private val router: Router,
    private val profileScreenMediator: ProfileScreenMediator
) : BaseRouter<FromLogin.GoTo> {
    override fun goTo(destination: FromLogin.GoTo) {
        when (destination) {
            is FromLogin.GoTo.NewRootScreen.Profile ->
                router.newRootScreen(profileScreenMediator.getProfileScreen(destination.profileName))
            is FromLogin.GoTo.Back ->
                router.exit()
        }
    }
}