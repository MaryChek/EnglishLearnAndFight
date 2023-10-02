package com.example.profile.di

import com.example.profile.navigation.ProfileRouter
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides

@Module
interface ProfileModule {

    companion object {

        @Provides
        fun createProfileRouter(router: Router): ProfileRouter =
            ProfileRouter(router)
    }
}