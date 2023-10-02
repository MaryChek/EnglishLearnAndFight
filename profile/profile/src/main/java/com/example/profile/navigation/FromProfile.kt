package com.example.profile.navigation

import com.example.basescreen.navigation.Action

sealed class FromProfile : Action() {
    sealed class GoTo : FromProfile(), Action.GoTo {
        sealed class Navigate : GoTo(), Action.Navigate {
            object Game : Navigate()
        }
    }
}
