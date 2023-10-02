package com.example.main.di

import com.example.core_api.providers.ProvidersFacade

object ActivityComponentHolder {
    private var activityComponent: MainActivityComponent? = null

    fun getActivityComponent(facade: ProvidersFacade): MainActivityComponent =
        activityComponent ?: MainActivityComponent.buildMainComponent(facade)
            .also { newComponent ->
                activityComponent = newComponent
            }

    fun clear() {
        activityComponent = null
    }
}