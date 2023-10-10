package com.example.game.data

import android.util.Log
import com.example.game.data.model.TranslatorRequestBody
import com.example.game.data.services.TranslateService
import com.example.game.data.services.WordService
import javax.inject.Inject

class WordsRepository @Inject constructor(
    private val translateService: TranslateService,
    private val worService: WordService
) {
    suspend fun getWord(): String? =
        worService.getRandomWord().firstOrNull()

    suspend fun translate(word: String, isRussian: Boolean): String {
        val requestJson = TranslatorRequestBody(
            word,
            source = takeIf { isRussian }?.let { RU_LOCALE } ?: EN_LOCALE,
            target = takeIf { isRussian }?.let { EN_LOCALE } ?: RU_LOCALE,
        )

        Log.d("requestJson: ", requestJson.toString())
        val res = translateService.translate(requestJson)
        Log.d("wordRepo", "$res")
        return res.translatedText
    }

    companion object {
        private const val RU_LOCALE = "ru"
        private const val EN_LOCALE = "en"
    }
}