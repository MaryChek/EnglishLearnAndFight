package com.example.game.viewmodel

import com.example.basescreen.viewmodels.BaseScreenViewModel
import com.example.game.model.CardGameScreenState
import com.example.game.navigation.FromGame

class GameViewModel : BaseScreenViewModel<CardGameScreenState, FromGame>(CardGameScreenState()) {
}