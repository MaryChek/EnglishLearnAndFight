package com.example.profile_api

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.profile.ProfileFragment
import com.github.terrakok.cicerone.androidx.Creator
import com.github.terrakok.cicerone.androidx.FragmentScreen

object ProfileScreen {
    data class Profile(val name: String) :
        FragmentScreen(fragmentCreator = object : Creator<FragmentFactory, Fragment> {
            override fun create(argument: FragmentFactory): Fragment =
                ProfileFragment.newInstance(name)
        })
}