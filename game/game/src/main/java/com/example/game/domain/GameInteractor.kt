package com.example.game.domain

import com.example.game.data.WordsRepository
import com.example.game.data.model.Result
import javax.inject.Inject
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.cancel
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.catch

class GameInteractor @Inject constructor(private val repository: WordsRepository) {
    private var fetchingWordsCounter = 0
    private val wordForGame = mutableListOf<CardWord>()
    private var job: Flow<Result<CardWord>>? = null

    suspend fun getNextWord(): Result<String> =
        if (wordForGame.size <= fetchingWordsCounter && wordForGame.size != MAX_GAME_WORDS) {
            delay(1000)
            getNextWord()
        } else if (fetchingWordsCounter < MAX_GAME_WORDS) {
            val ind = fetchingWordsCounter
            fetchingWordsCounter++
            Result.Success(wordForGame[ind].translate)
        } else {
            Result.End()
        }

    suspend fun listenWords() {
        while (wordForGame.size < MAX_GAME_WORDS) {
            job = listenLimitedWords(getLimit())
            job?.cancellable()?.collect { result ->
                if (wordForGame.size > MAX_GAME_WORDS) {
                    currentCoroutineContext().cancel(null)
                } else {
                    result.data?.let { wordForGame.add(it) }
                }
            }
            delay(60000)
        }
    }

    private fun getLimit(): Int =
        if (MAX_GAME_WORDS - wordForGame.size > MAX_TRANSLATE_REQUEST) {
            MAX_TRANSLATE_REQUEST
        } else {
            MAX_GAME_WORDS - wordForGame.size
        }

    private suspend fun listenLimitedWords(limit: Int) =
        flow {
            var newWordsCount = 0
            for (i in 1..MAX_TRANSLATE_REQUEST) {
                if (newWordsCount >= limit) {
                    currentCoroutineContext().cancel(null)
                }
                val newWord = repository.getWord()
                if (newWord != null) {
                    val translate = translate(newWord)
                    if (!newWord.equals(translate, false)) {
                        newWordsCount++
                        emit(Result.Success(CardWord(newWord, translate)) as Result<CardWord>)
                    }
                } else {
                    emit(Result.Error("GameInteractor"))
                }
            }
        }.cancellable().catch {
            emit(Result.Error("GameInteractor"))
        }

    fun closeGame() {
        wordForGame.clear()
        fetchingWordsCounter = 0
        job = null
    }

    fun checkTranslate(currentWord: String, answer: String): Boolean {
        return wordForGame.find {
            currentWord.equals(it.translate, false)
        }?.base == answer
    }

    private suspend fun translate(word: String): String =
        repository.translate(word, isRussian = false)

    fun getWordCount() = MAX_GAME_WORDS

    companion object {
        private const val MAX_GAME_WORDS = 10
        private const val MAX_TRANSLATE_REQUEST = 5
    }
}