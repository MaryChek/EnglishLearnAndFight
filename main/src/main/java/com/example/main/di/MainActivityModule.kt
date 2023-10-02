package com.example.main.di

import androidx.lifecycle.ViewModelProvider
import com.example.main.viewmodel.MainViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface MainActivityModule {

    @Binds
    fun bindsMainViewModelFactory(loginViewModelFactory: MainViewModelFactory): ViewModelProvider.Factory
}