package com.example.game.di

import com.example.game.presentation.navigation.GameScreensMediatorImpl
import com.example.game_api.GameScreensMediator
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
interface GameScreensModule {

    @Binds
    @IntoMap
    @ClassKey(GameScreensMediator::class)
    fun bindMediator(mediator: GameScreensMediatorImpl): Any
}