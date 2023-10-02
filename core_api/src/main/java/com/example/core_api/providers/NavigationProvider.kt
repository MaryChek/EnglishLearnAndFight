package com.example.core_api.providers

import com.github.terrakok.cicerone.NavigatorHolder

interface NavigationProvider : RouterProvider {
    fun provideNavigatorHolder() : NavigatorHolder
}