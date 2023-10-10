package com.example.game.data.services

import retrofit2.http.GET

interface WordService {
    @GET("word")
    suspend fun getRandomWord(): List<String>
}