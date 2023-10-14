package com.example.game.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.basescreen.models.Status
import com.example.basescreen.viewmodels.BaseScreenViewModel
import com.example.game.data.model.Result
import com.example.game.domain.GameInteractor
import com.example.game.presentation.model.CardGameScreenState
import com.example.game.presentation.navigation.FromCardGame
import kotlinx.coroutines.Dispatchers
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

    init {
        launch {
            interactor.listenWords()
        }
    }

    private fun launch(func: suspend () -> Unit) =
        viewModelScope.launch(Dispatchers.Main) {
            func.invoke()
        }

    fun onToolbarClick() =
        handleNavigate(FromCardGame.GoTo.Back)

    override fun onBackPressed() =
        Unit

    fun onViewCreated() =
        getNextWord()

    private fun updateModel(
        answer: String = model.userAnswer,
        currentWord: String = model.currentWord,
        shouldUpdateWord: Boolean = false,
        correctIndicator: Int = model.correctIndicator,
        wordIndex: Int = model.wordIndex,
        maxWords: Int = model.maxWords,
        wordIsLoading: Boolean = model.wordIsLoading,
        shouldUpdate: Boolean = true,
    ) {
        model = CardGameScreenState(
            answer, currentWord, shouldUpdateWord, correctIndicator, wordIndex, maxWords,
            wordIsLoading
        )
        if (shouldUpdate) {
            handleScreenState()
        }
        if (shouldUpdateWord) {
            clearUIAnswer()
        }
    }

    private fun clearUIAnswer() =
        handleNavigate(FromCardGame.Command.ClearAnswer)

    fun onNextClick() {
        updateModel(answer = "")
        getNextWord()
    }

    private fun getNextWord() =
        launch {
            if (!model.wordIsLoading) {
                updateModel(wordIsLoading = true, shouldUpdate = false)
                handleStatue(Status.LOADING)
                interactor.getNextWord().let(::handleResult)
            }
        }

    private fun handleResult(result: Result<String>) {
        handleStatue(result.status)
        when (result) {
            is Result.Success -> result.data?.let {
                updateModel(
                    currentWord = result.data,
                    shouldUpdateWord = true,
                    wordIndex = model.wordIndex + 1,
                    wordIsLoading = false
                )
            }
            is Result.End -> closeGame()
            else -> Unit
        }
    }

    private fun closeGame() {
        updateModel(wordIsLoading = false, shouldUpdate = false)
        interactor.closeGame()
        handleNavigate(FromCardGame.GoTo.Navigate.ToResult(model.correctIndicator))
    }

    fun onAnswerChange(answer: String) =
        updateModel(answer)

    fun onAnswerSubmitClick() {
        val isCurrent = interactor.checkTranslate(model.currentWord, model.userAnswer)
        if (isCurrent) {
            updateModel(
                correctIndicator = model.correctIndicator + 1,
                shouldUpdate = false
            )
        }
        clearUIAnswer()
        getNextWord()
    }
}