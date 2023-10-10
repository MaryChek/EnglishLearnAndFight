package com.example.game.di

import com.example.core_api.providers.NetworkProvider
import com.example.core_factory.NetworkComponentFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [NetworkProvider::class],
    modules = [BaseGameModule::class]
)
interface BaseGameComponent : InteractorProvider {
    companion object {
        fun create() : BaseGameComponent =
            DaggerBaseGameComponent.builder()
                .networkProvider(NetworkComponentFactory.createNetwork())
                .build()
    }
}