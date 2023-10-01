package com.example.englishlearnandfight

import android.app.Application
import com.example.core_api.providers.AppFacade
import com.example.core_api.providers.ProvidersFacade

class App: Application(), AppFacade {
    private lateinit var facadeComponent: FacadeComponent

    override fun onCreate() {
        super.onCreate()
        facadeComponent = FacadeComponent.init(this)
    }

    override fun getFacade(): ProvidersFacade =
        facadeComponent
}