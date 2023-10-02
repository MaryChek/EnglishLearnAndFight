package com.example.englishlearnandfight

import android.content.Context
import com.example.core_api.providers.AppProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface ApplicationComponent : AppProvider {

    @Component.Factory
    interface ContextFactory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

    companion object {
        fun create(context: Context): AppProvider =
            DaggerApplicationComponent.factory().create(context)
    }
}