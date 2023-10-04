package com.example.game.presentation.viewmodel

import com.example.basescreen.viewmodels.BaseScreenViewModel
import com.example.game.domain.GameInteractor
import com.example.game.presentation.model.CardGameScreenState
import com.example.game.presentation.navigation.FromCardGame

class GameViewModel(private val interactor: GameInteractor) :
    BaseScreenViewModel<CardGameScreenState, FromCardGame>(CardGameScreenState("")) {

    fun onToolbarClick() =
        onBackPressed()

    override fun onBackPressed() =
        handleNavigate(FromCardGame.GoTo.Back)

    fun onViewCreated() {
        val word = interactor.getNextWord()
        word?.let {
            updateModel(currentWord = word)
        }
    }

    private fun updateModel(
        answer: String? = model.userAnswer,
        currentWord: String = model.currentWord,
    ) {
        model = CardGameScreenState(answer, currentWord)
        handleScreenState()
    }

    fun onNextClick() {
        updateModel(answer = null)
        getNextWord()
    }

    private fun getNextWord() {
        val word = interactor.getNextWord()
        word?.let {
            updateModel(currentWord = word)
        } ?: closeGame()
    }

    private fun closeGame() {
        interactor.closeGame()
        handleNavigate(FromCardGame.GoTo.Navigate.ToResult("1"))
    }

    fun onAnswerChange(answer: String) =
        updateModel(answer)

    fun onAnswerSubmitClick() {
        getNextWord()
    }
}