package com.example.game.presentation.navigation

import com.example.basescreen.navigation.Action

sealed class FromGameResult : Action() {
    sealed class GoTo : FromGameResult(), Action.GoTo {

        sealed class BackTo : GoTo(), Action.BackTo {
            object StartGame : BackTo()

            object Profile : GoTo()
        }
    }
}
