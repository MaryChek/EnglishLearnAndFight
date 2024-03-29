package com.example.core.di

import com.example.core_api.providers.RouterNavigationProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NavigationModule::class]
)
interface NavigationComponent : RouterNavigationProvider