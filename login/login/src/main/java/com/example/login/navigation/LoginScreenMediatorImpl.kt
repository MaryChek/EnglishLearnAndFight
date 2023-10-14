package com.example.login.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.login.LoginFragment
import com.example.login_api.LoginScreenMediator
import com.github.terrakok.cicerone.androidx.Creator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import javax.inject.Inject

class LoginScreenMediatorImpl @Inject constructor() : LoginScreenMediator {

    override fun getLoginScreen(): FragmentScreen =
        Login

    companion object {

        object Login :
            FragmentScreen(fragmentCreator = object : Creator<FragmentFactory, Fragment> {
                override fun create(argument: FragmentFactory): Fragment =
                    LoginFragment()
            })
    }
}