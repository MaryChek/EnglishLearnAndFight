package com.example.login.di

import androidx.lifecycle.ViewModelProvider
import com.example.login.viewmodel.LoginViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface LoginModule {

    @Binds
    fun bindsLoginViewModelFactory(loginViewModelFactory: LoginViewModelFactory): ViewModelProvider.Factory
}