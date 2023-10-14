package com.example.game.presentation.routers

import com.example.basescreen.navigation.BaseRouter
import com.example.game.presentation.navigation.FromCardGame
import com.example.game.presentation.navigation.GameScreensMediatorImpl
import com.example.profile_api.ProfileScreenMediator
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class CardGameRouter @Inject constructor(
    private val router: Router,
    private val profileScreenMediator: ProfileScreenMediator
) : BaseRouter<FromCardGame.GoTo> {
    override fun goTo(destination: FromCardGame.GoTo) =
        when (destination) {
            is FromCardGame.GoTo.Navigate.ToResult ->
                router.navigateTo(GameScreensMediatorImpl.Companion.GameResult(destination.result))
            is FromCardGame.GoTo.Back ->
                router.backTo(profileScreenMediator.getProfileScreen(""))
        }
}