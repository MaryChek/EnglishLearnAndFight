package com.example.game.presentation.model

class CardGameScreenState(
    val userAnswer: String = "",
    val currentWord: String = "",
    val shouldUpdateWord: Boolean = false,
    val correctIndicator: Int,
    val wordIndex: Int,
    val maxWords: Int,
    val wordIsLoading: Boolean = false
) {
    val isBtnSubmitEnable: Boolean = userAnswer.isNotBlank()

    val tvWordCount: String = "$wordIndex/$maxWords"
}