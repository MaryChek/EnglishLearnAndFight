package com.example.game.presentation.routers

import com.example.basescreen.navigation.BaseRouter
import com.example.game.presentation.navigation.FromStartGame
import com.example.game.presentation.navigation.GameScreensMediatorImpl
import com.example.profile_api.ProfileScreenMediator
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class StartGameRouter @Inject constructor(
    private val router: Router,
    private val profileScreenMediator: ProfileScreenMediator
) : BaseRouter<FromStartGame.GoTo> {
    override fun goTo(destination: FromStartGame.GoTo) =
        when (destination) {
            is FromStartGame.GoTo.Navigate.Game ->
                router.navigateTo(GameScreensMediatorImpl.Companion.Game)
            is FromStartGame.GoTo.BackTo.Profile ->
                router.backTo(profileScreenMediator.getProfileScreen(""))
        }
}