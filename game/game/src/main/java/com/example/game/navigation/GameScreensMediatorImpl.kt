package com.example.game.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.game.fragments.CardGameFragment
import com.example.game.fragments.StartGameFragment
import com.example.game_api.GameScreensMediator
import com.github.terrakok.cicerone.androidx.Creator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import javax.inject.Inject

class GameScreensMediatorImpl @Inject constructor() : GameScreensMediator {
    override fun getGameScreen() = StartGame

    companion object {

        object Game : FragmentScreen(fragmentCreator = object : Creator<FragmentFactory, Fragment> {
            override fun create(argument: FragmentFactory): Fragment =
                CardGameFragment()
        })

        object StartGame :
            FragmentScreen(fragmentCreator = object : Creator<FragmentFactory, Fragment> {
                override fun create(argument: FragmentFactory): Fragment =
                    StartGameFragment()
            })
    }
}
