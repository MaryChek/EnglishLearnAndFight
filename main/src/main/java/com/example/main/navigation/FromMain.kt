package com.example.main.navigation

import com.example.basescreen.navigation.Action

sealed class FromMain : Action() {
    object Back : FromMain()

    sealed class GoTo : FromMain(), Action.GoTo {

        sealed class NewRootScreen : GoTo(), Action.NewRootScreen {
            object Login : NewRootScreen()

            class Profile(val name: String) : NewRootScreen()
        }
    }
}
