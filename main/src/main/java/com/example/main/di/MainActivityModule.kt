package com.example.main.di

import androidx.lifecycle.ViewModelProvider
import com.example.login_api.LoginScreenMediator
import com.example.main.viewmodel.MainViewModelFactory
import com.example.profile_api.ProfileScreenMediator
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
interface MainActivityModule {

    @Binds
    fun bindsMainViewModelFactory(loginViewModelFactory: MainViewModelFactory): ViewModelProvider.Factory

    companion object {

        @Provides
        fun provideProfileMediator(map: Map<Class<*>, @JvmSuppressWildcards Provider<Any>>): ProfileScreenMediator {
            return map[ProfileScreenMediator::class.java]!!.get() as ProfileScreenMediator
        }

        @Provides
        fun provideLoginMediator(map: Map<Class<*>, @JvmSuppressWildcards Provider<Any>>): LoginScreenMediator {
            return map[LoginScreenMediator::class.java]!!.get() as LoginScreenMediator
        }
    }
}