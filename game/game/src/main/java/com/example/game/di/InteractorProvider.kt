package com.example.game.di

import com.example.game.domain.GameInteractor

interface InteractorProvider {
    fun provideInteractor() : GameInteractor
}