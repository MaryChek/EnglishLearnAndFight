package com.example.game.presentation.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.game.presentation.fragments.CardGameFragment
import com.example.game.presentation.fragments.GameResultFragment
import com.example.game.presentation.fragments.StartGameFragment
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

        data class GameResult(val result: Int) : FragmentScreen(fragmentCreator = object : Creator<FragmentFactory, Fragment> {
            override fun create(argument: FragmentFactory): Fragment =
                GameResultFragment.newInstance(result)
        })
    }
}
