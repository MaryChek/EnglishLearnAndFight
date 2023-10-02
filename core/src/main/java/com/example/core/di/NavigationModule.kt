package com.example.core.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object NavigationModule {

    @Singleton
    @Provides
    fun createBaseRouter() : Cicerone<Router> =
        Cicerone.create()

    @Singleton
    @Provides
    fun createRouter(baseRouter: Cicerone<Router>) : Router =
        baseRouter.router

    @Singleton
    @Provides
    fun createRouterHoled(baseRouter: Cicerone<Router>) : NavigatorHolder =
        baseRouter.getNavigatorHolder()
}