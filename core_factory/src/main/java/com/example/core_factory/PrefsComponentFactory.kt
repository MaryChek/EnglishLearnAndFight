package com.example.core_factory

import com.example.core.data.di.DaggerPrefsComponent
import com.example.core_api.data.UserStorageProvider
import com.example.core_api.providers.AppProvider

object PrefsComponentFactory {
    fun createUserStorage(appProvider: AppProvider): UserStorageProvider =
        DaggerPrefsComponent.builder().appProvider(appProvider).build()
}