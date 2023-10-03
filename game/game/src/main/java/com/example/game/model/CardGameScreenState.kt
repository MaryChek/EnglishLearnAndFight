package com.example.game.model

class CardGameScreenState(
    val userAnswer: String? = null,
    val currentWord: String = ""
) {
    val isBtnSubmitEnable: Boolean = userAnswer.isNullOrBlank()
}