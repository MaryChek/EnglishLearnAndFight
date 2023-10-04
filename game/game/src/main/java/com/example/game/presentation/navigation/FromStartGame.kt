package com.example.game.presentation.navigation

import com.example.basescreen.navigation.Action

sealed class FromStartGame : Action() {
    sealed class GoTo : FromStartGame(), Action.GoTo {
        sealed class Navigate : GoTo(), Action.Navigate {
            object Game : Navigate()
        }
    }
}