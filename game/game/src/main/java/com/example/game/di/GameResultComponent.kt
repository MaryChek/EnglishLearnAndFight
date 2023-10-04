package com.example.game.di

import android.content.res.Resources
import com.example.core_api.providers.MainComponentProvider
import com.example.core_api.providers.RouterProvider
import com.example.game.presentation.fragments.GameResultFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [RouterProvider::class],
    modules = [GameModule::class]
)
interface GameResultComponent {
    fun inject(fragment: GameResultFragment)

    @Component.Factory
    interface Factory {
        fun create(
            routerProvider: RouterProvider,
            @BindsInstance result: Int,
            @BindsInstance resources: Resources
        ): GameResultComponent
    }

    companion object {
        fun create(
            activity: MainComponentProvider,
            result: Int,
            resources: Resources
        ): GameResultComponent =
            DaggerGameResultComponent.factory()
                .create(activity.getActivityComponent(), result, resources)
    }
}