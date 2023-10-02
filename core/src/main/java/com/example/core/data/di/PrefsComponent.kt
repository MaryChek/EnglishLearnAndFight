package com.example.core.data.di

import com.example.core_api.data.UserStorageProvider
import com.example.core_api.providers.AppProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [AppProvider::class],
    modules = [PrefsModule::class]
)
interface PrefsComponent: UserStorageProvider