package com.example.game.navigation

import com.example.basescreen.navigation.BaseRouter
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class StartGameRouter @Inject constructor(private val router: Router) : BaseRouter<FromStartGame.GoTo> {
    override fun goTo(destination: FromStartGame.GoTo) =
        when (destination) {
            is FromStartGame.GoTo.Navigate.Game ->
                router.navigateTo(GameScreensMediatorImpl.Companion.Game)
        }
}