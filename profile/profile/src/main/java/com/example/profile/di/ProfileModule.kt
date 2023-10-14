package com.example.profile.di

import com.example.game_api.GameScreensMediator
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
interface ProfileModule {

    companion object {

        @Provides
        fun provideGameMediator(map: Map<Class<*>, @JvmSuppressWildcards Provider<Any>>): GameScreensMediator {
            return map[GameScreensMediator::class.java]!!.get() as GameScreensMediator
        }
    }
}