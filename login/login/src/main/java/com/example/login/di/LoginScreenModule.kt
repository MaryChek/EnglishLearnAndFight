package com.example.login.di;

import com.example.login.navigation.LoginScreenMediatorImpl;
import com.example.login_api.LoginScreenMediator;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module
interface LoginScreenModule {

    @Binds
    @IntoMap
    @ClassKey(LoginScreenMediator::class)
    fun bindMediator(mediator: LoginScreenMediatorImpl): Any
}
