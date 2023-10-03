package com.example.profile.di

import com.example.core_api.providers.MainComponentProvider
import com.example.core_api.providers.ProvidersFacade
import com.example.core_api.providers.RouterProvider
import com.example.game_api.GameScreensMediator
import com.example.profile.ProfileFragment
import dagger.Component

@Component(
    dependencies = [ProvidersFacade::class, RouterProvider::class],
    modules = [ProfileModule::class]
)
interface ProfileComponent {
    fun inject(loginFragment: ProfileFragment)

    companion object {
        fun create(providersFacade: ProvidersFacade, activity: MainComponentProvider) : ProfileComponent =
            DaggerProfileComponent.builder()
                .providersFacade(providersFacade)
                .routerProvider(activity.getActivityComponent())
                .build()
    }
}