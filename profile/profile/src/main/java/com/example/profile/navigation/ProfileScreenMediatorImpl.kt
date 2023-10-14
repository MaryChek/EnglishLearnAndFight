package com.example.profile.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.profile.fragments.ProfileFragment
import com.example.profile_api.ProfileScreenMediator
import com.github.terrakok.cicerone.androidx.Creator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import javax.inject.Inject

class ProfileScreenMediatorImpl @Inject constructor() : ProfileScreenMediator {
    override fun getProfileScreen(name: String): FragmentScreen =
        Profile(name)

    companion object {

        data class Profile(val name: String) :
            FragmentScreen(fragmentCreator = object : Creator<FragmentFactory, Fragment> {
                override fun create(argument: FragmentFactory): Fragment =
                    ProfileFragment.newInstance(name)
            })
    }
}