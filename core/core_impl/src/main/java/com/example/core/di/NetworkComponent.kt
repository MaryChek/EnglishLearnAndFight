package com.example.core.di

import com.example.core_api.providers.NetworkProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NetworkModule::class]
)
interface NetworkComponent : NetworkProvider {
    companion object {
        fun init() : NetworkComponent =
            DaggerNetworkComponent.create()
    }
}