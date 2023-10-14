package com.example.profile.navigation

import com.example.basescreen.navigation.BaseRouter
import com.example.game_api.GameScreensMediator
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class ProfileRouter @Inject constructor(
    private val router: Router,
    private val gameMediator: GameScreensMediator
) : BaseRouter<FromProfile.GoTo> {
    override fun goTo(destination: FromProfile.GoTo) =
        when (destination) {
            is FromProfile.GoTo.Navigate.Game -> router.navigateTo(gameMediator.getGameScreen())
        }
}