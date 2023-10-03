package com.example.game.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.game.domain.GameInteractor
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class StartGameViewModelFactory @Inject constructor() : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StartGameViewModel() as T
    }
}

@Suppress("UNCHECKED_CAST")
class CardGameViewModelFactory @Inject constructor(private val interactor: GameInteractor) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GameViewModel(interactor) as T
    }
}