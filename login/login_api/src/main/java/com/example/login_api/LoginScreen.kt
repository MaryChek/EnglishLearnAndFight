package com.example.login_api

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.login.LoginFragment
import com.github.terrakok.cicerone.androidx.Creator
import com.github.terrakok.cicerone.androidx.FragmentScreen

object LoginScreen {
    object Login : FragmentScreen(fragmentCreator = object : Creator<FragmentFactory, Fragment> {
        override fun create(argument: FragmentFactory): Fragment =
            LoginFragment()
    })
}