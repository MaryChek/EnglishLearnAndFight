package com.example.game.di

import com.example.core_api.providers.MainComponentProvider
import com.example.core_api.providers.RouterProvider
import com.example.game.presentation.fragments.StartGameFragment
import dagger.Component

@Component(
    dependencies = [RouterProvider::class, GameNavigateProvider::class],
)
interface StartGameComponent {
    fun inject(startGameFragment: StartGameFragment)

    companion object {
        fun create(activity: MainComponentProvider, baseGameComponent: BaseGameComponent) : StartGameComponent =
            DaggerStartGameComponent.builder()
                .gameNavigateProvider(baseGameComponent)
                .routerProvider(activity.getActivityComponent())
                .build()
    }
}