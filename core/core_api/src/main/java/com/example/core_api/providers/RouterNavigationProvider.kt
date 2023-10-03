package com.example.core_api.providers

import com.github.terrakok.cicerone.NavigatorHolder

interface RouterNavigationProvider : RouterProvider {
    fun provideNavigatorHolder() : NavigatorHolder
}