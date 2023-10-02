package com.example.core.data.di

import android.content.Context
import com.example.core.data.storage.Prefs
import com.example.core.data.storage.UserStorageImpl
import com.example.core_api.data.UserStorage
import dagger.Module
import dagger.Provides

@Module
interface PrefsModule {

    companion object {

        @Provides
        fun providePrefs(context: Context) : Prefs =
            Prefs(context)

        @Provides
        fun provideUserStorage(prefs: Prefs): UserStorage =
            UserStorageImpl(prefs)
    }
}