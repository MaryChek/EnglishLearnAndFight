package com.example.game.data.model

import com.google.gson.annotations.SerializedName

data class TranslateAnswer(
    @field:SerializedName("translatedText")
    val translatedText: String
)