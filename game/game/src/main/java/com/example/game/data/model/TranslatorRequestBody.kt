package com.example.game.data.model

class TranslatorRequestBody(
    val q: String,
    val source: String,
    val target: String,
    val format: String = "text"
)