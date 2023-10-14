package com.example.game.di

import com.example.core_api.providers.AppFacade

object BaseGameComponentHolder {
    private var gameComponent: com.example.game.di.BaseGameComponent? = null

    fun getComponent(appFacade: AppFacade) : com.example.game.di.BaseGameComponent =
        gameComponent ?: com.example.game.di.BaseGameComponent.create(appFacade)

    fun clear() {
        gameComponent = null
    }
}