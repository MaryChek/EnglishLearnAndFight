package com.example.main.di

import com.example.core_api.providers.RouterNavigationProvider
import com.example.core_api.providers.ProvidersFacade
import com.example.core_api.providers.RouterProvider
import com.example.core_factory.NavigationComponentFactory
import com.example.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [RouterNavigationProvider::class, ProvidersFacade::class],
    modules = [MainActivityModule::class]
)
interface MainActivityComponent : RouterProvider {
    fun inject(mainActivity: MainActivity)

    companion object {
        fun buildMainComponent(providersFacade: ProvidersFacade) : MainActivityComponent =
            DaggerMainActivityComponent.builder()
                .providersFacade(providersFacade)
                .routerNavigationProvider(NavigationComponentFactory.createNavigationRouterComponent())
                .build()
    }
}