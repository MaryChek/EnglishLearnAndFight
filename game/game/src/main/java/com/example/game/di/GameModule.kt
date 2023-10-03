package com.example.game.di

import com.example.game.domain.GameInteractor
import dagger.Module
import dagger.Provides

@Module
interface GameModule {
    companion object {
        @Provides
        fun createInteractor() : GameInteractor =
            GameInteractor()
    }
}