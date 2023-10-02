package com.example.core.di

import com.example.core_api.providers.NavigationProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NavigationModule::class]
)
interface NavigationComponent : NavigationProvider