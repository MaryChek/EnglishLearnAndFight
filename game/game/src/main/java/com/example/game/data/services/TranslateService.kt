package com.example.game.data.services

import com.example.game.data.model.TranslateAnswer
import com.example.game.data.model.TranslatorRequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface TranslateService {
    @POST("translate")
    suspend fun translate(@Body translatedJson: TranslatorRequestBody): TranslateAnswer
}