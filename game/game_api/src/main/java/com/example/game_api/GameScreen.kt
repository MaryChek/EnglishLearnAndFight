package com.example.game_api

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.game.StartGameFragment
import com.github.terrakok.cicerone.androidx.Creator
import com.github.terrakok.cicerone.androidx.FragmentScreen

object GameScreen {
    object Game : FragmentScreen(fragmentCreator = object : Creator<FragmentFactory, Fragment> {
        override fun create(argument: FragmentFactory): Fragment =
            StartGameFragment()
    })
}