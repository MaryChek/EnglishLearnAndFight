package com.example.login.navigation

import com.example.basescreen.navigation.Action

sealed class FromLogin : Action() {
    sealed class GoTo : FromLogin(), Action.GoTo {
        object Back : GoTo()

        sealed class NewRootScreen : GoTo(), Action.NewRootScreen {
            class Profile(val profileName: String) : NewRootScreen()
        }
    }
}