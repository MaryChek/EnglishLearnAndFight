package com.example.login.di

import androidx.lifecycle.ViewModelProvider
import com.example.login.navigation.LoginRouter
import com.example.login.viewmodel.LoginViewModelFactory
import com.github.terrakok.cicerone.Router
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface LoginModule {

    @Binds
    fun bindsLoginViewModelFactory(loginViewModelFactory: LoginViewModelFactory): ViewModelProvider.Factory

    companion object {

        @Provides
        fun createLoginRouter(router: Router): LoginRouter =
            LoginRouter(router)
    }
}