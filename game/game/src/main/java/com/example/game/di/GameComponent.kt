package com.example.game.di

import com.example.core_api.providers.MainComponentProvider
import com.example.core_api.providers.RouterProvider
import com.example.game.fragments.CardGameFragment
import dagger.Component

@Component(
    dependencies = [RouterProvider::class],
    modules = [GameModule::class]
)
interface GameComponent {
    fun inject(gameFragment: CardGameFragment)

    companion object {
        fun create(activity: MainComponentProvider) : GameComponent =
            DaggerGameComponent.builder()
                .routerProvider(activity.getActivityComponent())
                .build()
    }
}