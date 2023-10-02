package com.example.main.navigation

import com.example.basescreen.navigation.Action

sealed class FromMain {
    sealed class GoTo : FromMain(), Action.GoTo {
        object Back : GoTo()

        sealed class NewRootScreen : GoTo(), Action.NewRootScreen {
            object Login : NewRootScreen()
        }
    }
}
