package com.example.core_factory

import com.example.core.di.DaggerNavigationComponent
import com.example.core_api.providers.RouterNavigationProvider

object NavigationComponentFactory {
    fun createNavigationRouterComponent() : RouterNavigationProvider =
        DaggerNavigationComponent.create()
}