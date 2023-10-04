package com.example.game.presentation.navigation

import com.example.basescreen.navigation.Action

sealed class FromCardGame : Action() {
    sealed class GoTo : FromCardGame(), Action.GoTo {
        object Back: GoTo()

        sealed class Navigate : GoTo(), Action.Navigate {
            class ToResult(val result: String) : Navigate()
        }
    }
}