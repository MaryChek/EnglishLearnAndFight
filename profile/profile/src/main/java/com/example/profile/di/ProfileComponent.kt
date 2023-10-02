package com.example.profile.di

import com.example.core_api.data.UserStorageProvider
import com.example.core_api.providers.MainComponentProvider
import com.example.core_api.providers.ProvidersFacade
import com.example.core_api.providers.RouterProvider
import com.example.core_factory.PrefsComponentFactory
import com.example.profile.ProfileFragment
import dagger.Component

@Component(
    dependencies = [ProvidersFacade::class, UserStorageProvider::class, RouterProvider::class],
    modules = [ProfileModule::class]
)
interface ProfileComponent {
    fun inject(loginFragment: ProfileFragment)

    companion object {
        fun create(providersFacade: ProvidersFacade, activity: MainComponentProvider) : ProfileComponent =
            DaggerProfileComponent.builder()
                .providersFacade(providersFacade)
                .routerProvider(activity.getActivityComponent())
                .userStorageProvider(PrefsComponentFactory.createUserStorage(providersFacade))
                .build()
    }
}