package com.example.main.di

import com.example.core_api.providers.NavigationProvider
import com.example.core_factory.NavigationComponentFactory
import com.example.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [NavigationProvider::class],
    modules = [MainActivityModule::class]
)
interface MainActivityComponent {
    fun inject(mainActivity: MainActivity)

    companion object {
        fun buildMainComponent() : MainActivityComponent =
            DaggerMainActivityComponent.builder()
                .navigationProvider(NavigationComponentFactory.createNavigationRouterComponent())
                .build()
    }
}