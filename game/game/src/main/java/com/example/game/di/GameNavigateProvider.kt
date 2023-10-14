package com.example.game.di

import com.example.profile_api.ProfileScreenMediator

interface GameNavigateProvider {
    fun provideProfileScreenMediator() : ProfileScreenMediator
}