package com.example.game.presentation.viewmodel

import com.example.basescreen.viewmodels.BaseScreenViewModel
import com.example.game.presentation.model.StartGameScreenState
import com.example.game.presentation.navigation.FromStartGame

class StartGameViewModel : BaseScreenViewModel<StartGameScreenState, FromStartGame>(
    StartGameScreenState()
) {
    fun onStartClick() =
        handleNavigate(FromStartGame.GoTo.Navigate.Game)
}