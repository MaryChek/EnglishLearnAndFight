package com.example.login.di

import com.example.core_api.data.UserStorageProvider
import com.example.core_api.providers.ProvidersFacade
import com.example.core_factory.PrefsComponentFactory
import com.example.login.LoginFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [ProvidersFacade::class, UserStorageProvider::class],
    modules = [LoginModule::class]
)
interface LoginComponent {

    fun inject(loginFragment: LoginFragment)

    companion object {
        fun create(providersFacade: ProvidersFacade) : LoginComponent =
            DaggerLoginComponent.builder()
                .providersFacade(providersFacade)
                .userStorageProvider(PrefsComponentFactory.createUserStorage(providersFacade))
                .build()
    }
}