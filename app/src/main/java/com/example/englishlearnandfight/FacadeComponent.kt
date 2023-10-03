package com.example.englishlearnandfight

import android.app.Application
import com.example.core_api.data.UserStorageProvider
import com.example.core_api.providers.AppProvider
import com.example.core_api.providers.ProvidersFacade
import com.example.core_factory.PrefsComponentFactory
import com.example.game.di.GameScreensModule
import dagger.Component

@Component(
    dependencies = [AppProvider::class, UserStorageProvider::class],
    modules = [GameScreensModule::class]
)
interface FacadeComponent : ProvidersFacade {

    companion object {

        fun init(application: Application) : FacadeComponent {
            val appProvider = ApplicationComponent.create(application)
            return DaggerFacadeComponent.builder()
                .appProvider(appProvider)
                .userStorageProvider(PrefsComponentFactory.createUserStorage(appProvider))
                .build()
        }
    }
}