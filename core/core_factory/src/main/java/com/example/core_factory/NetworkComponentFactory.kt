package com.example.core_factory

import com.example.core.di.NetworkComponent
import com.example.core_api.providers.NetworkProvider

object NetworkComponentFactory {
    fun createNetwork() : NetworkProvider =
        NetworkComponent.init()
}