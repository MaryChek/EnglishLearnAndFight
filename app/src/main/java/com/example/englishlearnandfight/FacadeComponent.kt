package com.example.englishlearnandfight

import android.app.Application
import com.example.core_api.data.UserStorageProvider
import com.example.core_api.providers.AppProvider
import com.example.core_api.providers.ProvidersFacade
import dagger.Component

@Component(
    dependencies = [AppProvider::class]
)
interface FacadeComponent : ProvidersFacade {

    companion object {

        fun init(application: Application) : FacadeComponent =
            DaggerFacadeComponent.builder()
                .appProvider(ApplicationComponent.create(application))
                .build()
    }
}