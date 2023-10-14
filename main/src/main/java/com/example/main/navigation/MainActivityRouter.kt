package com.example.main.navigation

import com.example.basescreen.navigation.BaseRouter
import com.example.login_api.LoginScreenMediator
import com.example.profile_api.ProfileScreenMediator
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class MainActivityRouter @Inject constructor(
    private val router: Router,
    private val profileScreenMediator: ProfileScreenMediator,
    private val loginScreenMediator: LoginScreenMediator,
) : BaseRouter<FromMain.GoTo>{
    override fun goTo(destination: FromMain.GoTo) =
        when (destination) {
            is FromMain.GoTo.NewRootScreen.Login ->
                router.newRootScreen(loginScreenMediator.getLoginScreen())
            is FromMain.GoTo.NewRootScreen.Profile ->
                router.newRootScreen(profileScreenMediator.getProfileScreen(destination.name))
        }
}