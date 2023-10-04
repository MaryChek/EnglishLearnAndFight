package com.example.game.domain

class GameInteractor {
    private var counter = 0

    private val words = listOf(
        "Яблоко",
        "Апельсин",
        "Мандарин",
        "Груша",
        "Лимон",
        "Арбуз",
        "Клюква",
        "Сок",
        "Носок",
        "Бутылка",
        "Вода"
    )
    private val wordsTranslate = listOf(
        "Apple",
        "OrAnge",
        "Mandarin",
        "pear",
        "Lemon",
        "Watermelon",
        "Cranberry",
        "Juice",
        "Sock",
        "Bottle",
        "Water"
    )

    fun getNextWord(): String? {
        var newWord: String? = null
        if (counter < MAX_GAME_WORDS) {
            newWord = words[counter]
            counter++
        } else {
            closeGame()
        }
        return newWord
    }

    fun closeGame() {
        counter = 0
    }

    fun checkTranslate(currentWord: String, translate: String): Boolean {
        val wordIndex = words.indexOf(currentWord)
        return if (wordIndex != -1) {
            wordsTranslate[wordIndex].equals(translate.trim(), true)
        } else {
            false
        }
    }

    fun getWordCount() = MAX_GAME_WORDS

    companion object {
        private const val MAX_GAME_WORDS = 10
    }
}