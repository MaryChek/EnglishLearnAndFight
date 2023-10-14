package com.example.profile.di

import com.example.profile.navigation.ProfileScreenMediatorImpl
import com.example.profile_api.ProfileScreenMediator
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
interface ProfileScreenModule {

    @Binds
    @IntoMap
    @ClassKey(ProfileScreenMediator::class)
    fun bindMediator(mediator: ProfileScreenMediatorImpl): Any
}