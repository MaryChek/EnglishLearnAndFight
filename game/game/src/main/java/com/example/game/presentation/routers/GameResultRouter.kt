package com.example.game.presentation.routers

import com.example.basescreen.navigation.BaseRouter
import com.example.game.presentation.navigation.FromGameResult
import com.example.game.presentation.navigation.GameScreensMediatorImpl
import com.example.profile_api.ProfileScreenMediator
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class GameResultRouter @Inject constructor(
    private val router: Router,
    private val profileScreenMediator: ProfileScreenMediator
) : BaseRouter<FromGameResult.GoTo> {
    override fun goTo(destination: FromGameResult.GoTo) =
        when (destination) {
            is FromGameResult.GoTo.BackTo.StartGame ->
                router.backTo(GameScreensMediatorImpl.Companion.StartGame)
            is FromGameResult.GoTo.BackTo.Profile ->
                router.backTo(profileScreenMediator.getProfileScreen(""))
        }
}