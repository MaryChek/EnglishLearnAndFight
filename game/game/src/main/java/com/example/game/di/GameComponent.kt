package com.example.game.di

import com.example.core_api.providers.MainComponentProvider
import com.example.core_api.providers.RouterProvider
import com.example.game.domain.GameInteractor
import com.example.game.presentation.fragments.CardGameFragment
import dagger.Component

@Component(
    dependencies = [RouterProvider::class, InteractorProvider::class],
)
interface GameComponent {
    fun inject(gameFragment: CardGameFragment)

    companion object {
        fun create(activity: MainComponentProvider, baseGameComponent: BaseGameComponent) : GameComponent =
            DaggerGameComponent.builder()
                .routerProvider(activity.getActivityComponent())
                .interactorProvider(baseGameComponent)
                .build()
    }

    fun provideInteractor() : GameInteractor
}