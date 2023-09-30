package com.example.englishlearnandfight

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface ApplicationComponent {

    @Component.Factory
    interface ContextFactory {
        fun create(@BindsInstance context: Context) : ApplicationComponent
    }

    companion object {
        fun getAppComponent(context: Context) : ApplicationComponent =
            DaggerApplicationComponent.factory().create(context)
    }

    fun provideAppContext(): Context
}