package com.example.login.di

import androidx.lifecycle.ViewModelProvider
import com.example.login.viewmodel.LoginViewModelFactory
import com.example.profile_api.ProfileScreenMediator
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
interface LoginModule {

    //todo это не нужно, кажется
    @Binds
    fun bindsLoginViewModelFactory(loginViewModelFactory: LoginViewModelFactory): ViewModelProvider.Factory

    companion object {

        @Provides
        fun provideProfileMediator(map: Map<Class<*>, @JvmSuppressWildcards Provider<Any>>): ProfileScreenMediator {
            return map[ProfileScreenMediator::class.java]!!.get() as ProfileScreenMediator
        }
    }
}