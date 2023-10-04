package com.example.game.presentation.viewmodel

import android.content.res.Resources
import com.example.basescreen.viewmodels.BaseScreenViewModel
import com.example.game.domain.GameInteractor
import com.example.game.presentation.model.GameResultScreenState
import com.example.game.presentation.navigation.FromGameResult

class GameResultViewModel(
    result: Int,
    private val interactor: GameInteractor,
    resources: Resources,
) : BaseScreenViewModel<GameResultScreenState, FromGameResult>(
    GameResultScreenState(resources, result)
) {
    fun onViewCreated() {
        val maxResult = interactor.getWordCount()
        updateModel(maxResult)
    }

    private fun updateModel(
        maxResult: Int? = model.maxResult,
        result: Int = model.currentResult,
        resources: Resources = model.resources
    ) {
        model = GameResultScreenState(resources, result, maxResult)
        handleScreenState()
    }

    fun onRetryClick() =
        handleNavigate(FromGameResult.GoTo.BackTo.StartGame)

    fun onCloseGameClick() =
        handleNavigate(FromGameResult.GoTo.BackTo.Profile)
}