package com.example.profile.viewmodel

import com.example.basescreen.viewmodels.BaseScreenViewModel
import com.example.profile.navigation.FromProfile

class ProfileViewModel : BaseScreenViewModel<Any, FromProfile>("") {
    fun onStartTrainClick() =
        handleNavigate(FromProfile.GoTo.Navigate.Game)

    fun onStartFightClick() {
        //TODO navigate to fight screen
    }

    fun onNameClick() {
        //TODO add change name fragment
    }

    fun onGameHistoryClick() {
        //TODO add change name fragment and navigate
    }

    fun onSettingsClick() {
        //TODO add setting game fragment and navigate
    }
}