package com.example.profile.navigation

import com.example.basescreen.navigation.BaseRouter
import com.example.game_api.GameScreen
import com.github.terrakok.cicerone.Router

class ProfileRouter(private val router: Router) : BaseRouter<FromProfile.GoTo> {
    override fun goTo(destination: FromProfile.GoTo) =
        when (destination) {
            is FromProfile.GoTo.Navigate.Game -> router.navigateTo(GameScreen.Game)
        }
}