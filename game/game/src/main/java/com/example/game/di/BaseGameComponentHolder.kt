package com.example.game.di

object BaseGameComponentHolder {
    private var gameComponent: BaseGameComponent? = null

    fun getComponent() : BaseGameComponent =
        gameComponent ?: BaseGameComponent.create()

    fun clear() {
        gameComponent = null
    }
}