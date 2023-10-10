package com.example.game.domain

import android.util.Log
import com.example.game.data.WordsRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.flow
import com.example.game.data.model.Result
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
            Log.d(
                "GameInteractor", "GetNextWord : пока ничего, пусто...\n" +
                        "текущее состояне:\n\tколичество слов всего: ${wordForGame.size}\n\n\t" +
                        "Пока ожидаю $fetchingWordsCounter-е слово\n"
            )
            delay(1000)
            getNextWord()
        } else if (fetchingWordsCounter < MAX_GAME_WORDS) {
            Log.d(
                "GameInteractor",
                "GetNextWord : Кажется появилось слово,\nведь количесво [${wordForGame.size}] стал больше ожидаемого [$fetchingWordsCounter]\n"
            )
            val ind = fetchingWordsCounter
            fetchingWordsCounter++
            Log.d(
                "GameInteractor",
                "GetNextWord : Слово получено: ${wordForGame[ind].translate}\n" +
                        "текущее состояне:\n\tколичество слов всего: ${wordForGame.size}\n\n\t" +
                        "Теперь ожидаю $fetchingWordsCounter-е слово\n"
            )
            Result.Success(wordForGame[ind].translate)
        } else {
            Log.d(
                "GameInteractor",
                "GetNextWord : Кажется слова закончились,\nих количесво [${wordForGame.size}] и ожидаемое [$fetchingWordsCounter] бодьше $MAX_GAME_WORDS\n"
            )
            Result.End()
        }

    suspend fun listenWords() {
        var i = MAX_TRANSLATE_REQUEST
        while (i < MAX_GAME_WORDS + MAX_TRANSLATE_REQUEST) {
            Log.d(
                "GameInteractor",
                "ListenWords : Начинаем новый поиск слов, лемит не должен мешать\n" +
                        "Попробуем отыскать еще ${getLimit()} слов"
            )
            job = listenLimitedWords(getLimit())
            job?.cancellable()?.collect { result ->
                if (wordForGame.size > MAX_GAME_WORDS) {
                    Log.d("GameInteractor", "ListenWords : Кажется, все слова набраны: \n")
                    currentCoroutineContext().cancel(null)
                } else {
                    result.data?.let {
                        Log.d("GameInteractor", "ListenWords : Добавляю словечко: $it\n")
                        wordForGame.add(it)
                    }
                }
            }
            Log.d(
                "GameInteractor",
                "ListenWords : Так так, нашли все слова, что могли: ${wordForGame.size}\n" +
                        "Но про лимит не стоить забывать, уходим в спячку\n" +
                        "Следующая партия слов будет: текущее [${wordForGame.size}] + лимит [$MAX_TRANSLATE_REQUEST]\n" +
                        "Надеюсь, что в сумме это не больше [$MAX_GAME_WORDS]"
            )
            i = wordForGame.size
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
                    Log.d("GameInteractor", "ListenWords : Все слова в пределах лимита набраны: \n")
                    currentCoroutineContext().cancel(null)
                }
                Log.d("GameInteractor", "ListenWords : Ищу рандомное $i-е слово\n")
                val newWord = repository.getWord()
                Log.d("GameInteractor", "ListenWords : Получено! Слово: $newWord\n")
                if (newWord != null) {
                    Log.d("GameInteractor", "ListenWords : Начинаю переводить...\n")
                    val translate = translate(newWord)
                    Log.d("GameInteractor", "ListenWords : Перевод получен: $translate\n")
                    if (!newWord.equals(translate, false)) {
                        Log.d(
                            "GameInteractor",
                            "ListenWords : Хорошо, что перевод отличный возвращаю результат!"
                        )
                        newWordsCount++
                        emit(Result.Success(CardWord(newWord, translate)) as Result<CardWord>)
                    }
                } else {
                    Log.d(
                        "GameInteractor",
                        "ListenWords : Кажется, что-то пошло не так, не удалось получить слово, но попробуем еще раз\n"
                    )
                    emit(Result.Error("GameInteractor"))
                }
            }
        }.cancellable().catch {
                Log.d(
                    "GameInteractor",
                    "ListenWords : Кажется, что-то пошло не так, как мы вообще сбда попали?\n"
                )
                emit(Result.Error("GameInteractor"))
            }

    fun closeGame() {
        Log.d("GameInteractor", "CloseGame : Все, заканчиваем игру")
        wordForGame.clear()
        fetchingWordsCounter = 0
        job = null
    }

    fun checkTranslate(currentWord: String, answer: String): Boolean {
        Log.d(
            "GameInteractor", "CheckTranslate : отправлено слово на проверочу:\n" +
                    "пользователь думает, что переводом слова [$currentWord] будет [$answer]\n" +
                    "посмотрим, есть ли у нас такой перевод?"
        )
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