package com.example.login.di

import com.example.core_api.providers.MainComponentProvider
import com.example.core_api.providers.ProvidersFacade
import com.example.core_api.providers.RouterProvider
import com.example.login.LoginFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [ProvidersFacade::class, RouterProvider::class],
    modules = [LoginModule::class]
)
interface LoginComponent {

    fun inject(loginFragment: LoginFragment)

    companion object {
        fun create(providersFacade: ProvidersFacade, activity: MainComponentProvider) : LoginComponent =
            DaggerLoginComponent.builder()
                .providersFacade(providersFacade)
                .routerProvider(activity.getActivityComponent())
                .build()
    }
}