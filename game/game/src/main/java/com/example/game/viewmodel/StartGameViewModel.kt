package com.example.game.viewmodel

import com.example.basescreen.viewmodels.BaseScreenViewModel
import com.example.game.model.StartGameScreenState
import com.example.game.navigation.FromStartGame

class StartGameViewModel : BaseScreenViewModel<StartGameScreenState, FromStartGame>(
    StartGameScreenState()
) {
}