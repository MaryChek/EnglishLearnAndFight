package com.example.game.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.basescreen.viewmodels.BaseScreenViewModel
import com.example.game.domain.GameInteractor
import com.example.game.presentation.model.CardGameScreenState
import com.example.game.presentation.navigation.FromCardGame
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class GameViewModel(private val interactor: GameInteractor) :
    BaseScreenViewModel<CardGameScreenState, FromCardGame>(
        CardGameScreenState(
            userAnswer = "",
            wordIndex = 0,
            correctIndicator = 0,
            maxWords = interactor.getWordCount()
        )
    ) {

    private var refreshJob: Job? = null

    private fun launch(func: suspend () -> Unit) =
        viewModelScope.launch(Dispatchers.Main) {
            func.invoke()
        }

    fun onToolbarClick() =
        handleNavigate(FromCardGame.GoTo.Back)

    override fun onBackPressed() =
        Unit

    fun onViewCreated() =
        setNewWord()

    private fun updateModel(
        answer: String = model.userAnswer,
        currentWord: String = model.currentWord,
        shouldUpdateWord: Boolean = false,
        shouldUpdate: Boolean = true,
        correctIndicator: Int = model.correctIndicator,
        wordIndex: Int = model.wordIndex,
        maxWords: Int = model.maxWords
    ) {
        model = CardGameScreenState(
            answer, currentWord, shouldUpdateWord, correctIndicator, wordIndex, maxWords
        )
        if (shouldUpdate) {
            handleScreenState()
        }
        if (shouldUpdateWord) {
            handleNavigate(FromCardGame.Command.ClearAnswer)
        }
    }

    fun onNextClick() {
        updateModel(answer = "")
        getNextWord()
    }

    private fun getNextWord() =
        setNewWord() ?: closeGame()

    private fun setNewWord(): String? {
        val word = interactor.getNextWord()
        word?.apply {
            updateModel(
                currentWord = word,
                shouldUpdateWord = true,
                wordIndex = model.wordIndex + 1
            )
        }
        return word
    }

    private fun closeGame() {
        interactor.closeGame()
        handleNavigate(FromCardGame.GoTo.Navigate.ToResult(model.correctIndicator))
    }

    fun onAnswerChange(answer: String) =
        updateModel(answer)

    fun onAnswerSubmitClick() {
        launch {
            val isCurrent = interactor.checkTranslate(model.currentWord, model.userAnswer)
            if (isCurrent) {
                updateModel(correctIndicator = model.correctIndicator + 1, shouldUpdate = false)
            }
            getNextWord()
        }
    }
}