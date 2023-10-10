package com.example.core_api.providers

import com.example.core_api.qualifier.TranslateRetrofit
import com.example.core_api.qualifier.WordRetrofit
import retrofit2.Retrofit

interface NetworkProvider {
    @WordRetrofit
    fun provideWordsRetrofit() : Retrofit

    @TranslateRetrofit
    fun provideTranslateRetrofit() : Retrofit
}