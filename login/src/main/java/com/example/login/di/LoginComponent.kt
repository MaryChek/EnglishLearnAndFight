package com.example.login.di

import com.example.core_api.providers.ProvidersFacade
import com.example.login.LoginFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [LoginModule::class],
    dependencies = [ProvidersFacade::class]
)
interface LoginComponent {

    fun inject(loginFragment: LoginFragment)

    companion object {
        fun create(providersFacade: ProvidersFacade) : LoginComponent =
            DaggerLoginComponent.builder()
                .providersFacade(providersFacade)
                .build()
    }
}