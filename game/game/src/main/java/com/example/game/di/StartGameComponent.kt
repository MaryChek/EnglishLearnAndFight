package com.example.game.di

import com.example.core_api.providers.MainComponentProvider
import com.example.core_api.providers.RouterProvider
import com.example.game.fragments.StartGameFragment
import dagger.Component

@Component(
    dependencies = [RouterProvider::class],
    modules = [GameModule::class]
)
interface StartGameComponent {
    fun inject(startGameFragment: StartGameFragment)

    companion object {
        fun create(activity: MainComponentProvider) : StartGameComponent =
            DaggerStartGameComponent.builder()
                .routerProvider(activity.getActivityComponent())
                .build()
    }
}