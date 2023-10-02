package com.example.core_api.providers

import com.github.terrakok.cicerone.Router

interface RouterProvider {
    fun provideRouter() : Router
}