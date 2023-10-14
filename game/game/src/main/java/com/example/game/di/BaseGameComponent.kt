package com.example.game.di

import com.example.core_api.providers.AppFacade
import com.example.core_api.providers.NetworkProvider
import com.example.core_api.providers.ProvidersFacade
import com.example.core_factory.NetworkComponentFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [NetworkProvider::class, ProvidersFacade::class],
    modules = [BaseGameModule::class]
)
interface BaseGameComponent : InteractorProvider, GameNavigateProvider {
    companion object {
        fun create(appFacade: AppFacade) : BaseGameComponent =
            DaggerBaseGameComponent.builder()
                .networkProvider(NetworkComponentFactory.createNetwork())
                .providersFacade(appFacade.getFacade())
                .build()
    }
}