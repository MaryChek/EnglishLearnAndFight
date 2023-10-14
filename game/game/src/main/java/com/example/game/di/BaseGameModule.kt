package com.example.game.di

import com.example.core_api.qualifier.TranslateRetrofit
import com.example.core_api.qualifier.WordRetrofit
import com.example.game.data.WordsRepository
import com.example.game.data.services.TranslateService
import com.example.game.data.services.WordService
import com.example.game.domain.GameInteractor
import com.example.profile_api.ProfileScreenMediator
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Provider
import javax.inject.Singleton

@Module
interface BaseGameModule {
    companion object {
        @Provides
        @Singleton
        fun createTranslateApi(@TranslateRetrofit retrofit: Retrofit): TranslateService =
            retrofit.create(TranslateService::class.java)

        @Provides
        @Singleton
        fun createWordApi(@WordRetrofit retrofit: Retrofit): WordService =
            retrofit.create(WordService::class.java)

        @Provides
        @Singleton
        fun createRepository(
            wordApi: WordService,
            translateApi: TranslateService
        ): WordsRepository =
            WordsRepository(translateApi, wordApi)

        @Provides
        @Singleton
        fun createInteractor(repository: WordsRepository): GameInteractor =
            GameInteractor(repository)

        @Provides
        @Singleton
        fun provideProfileMediator(map: Map<Class<*>, @JvmSuppressWildcards Provider<Any>>): ProfileScreenMediator {
            return map[ProfileScreenMediator::class.java]!!.get() as ProfileScreenMediator
        }
    }
}