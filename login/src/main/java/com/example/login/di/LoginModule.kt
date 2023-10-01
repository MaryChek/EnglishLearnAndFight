package com.example.login.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.login.LoginFragment
import com.example.login.viewmodel.LoginViewModel
import com.example.login.viewmodel.LoginViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface LoginModule {

    @Binds
    fun bindsLoginViewModelFactory(loginViewModelFactory: LoginViewModelFactory): ViewModelProvider.Factory
}