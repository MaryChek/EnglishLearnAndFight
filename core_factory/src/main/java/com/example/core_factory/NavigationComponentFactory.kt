package com.example.core_factory

import com.example.core.di.DaggerNavigationComponent
import com.example.core_api.providers.NavigationProvider

object NavigationComponentFactory {
    fun createNavigationRouterComponent() : NavigationProvider =
        DaggerNavigationComponent.create()
}