package com.example.game.viewmodel

import android.util.Log
import com.example.basescreen.viewmodels.BaseScreenViewModel
import com.example.game.domain.GameInteractor
import com.example.game.model.CardGameScreenState
import com.example.game.navigation.FromGame

class GameViewModel(private val gameInteractor: GameInteractor) :
    BaseScreenViewModel<CardGameScreenState, FromGame>(CardGameScreenState("")) {

    fun onViewCreated() {
        val word = gameInteractor.getNextWord()
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
        val word = gameInteractor.getNextWord()
        word?.let {
            updateModel(currentWord = word)
        } ?: Log.d("GameViewModel", "game ower")
    }

    fun onAnswerChange(answer: String) =
        updateModel(answer)

    fun onAnswerSubmitClick() {
        getNextWord()
    }
}