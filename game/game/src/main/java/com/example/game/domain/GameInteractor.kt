package com.example.game.domain

class GameInteractor {
    private var counter = 0

    private val words = listOf("Яблоко", "Апельсин", "Мандарин", "Груша", "Лимон", "Арбуз", "Клюква", "Сок", "Носок", "Бутылка", "Вода",)

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

    companion object {
        private const val MAX_GAME_WORDS = 10
    }
}