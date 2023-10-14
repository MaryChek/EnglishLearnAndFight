package com.example.profile_api

import com.github.terrakok.cicerone.androidx.FragmentScreen

interface ProfileScreenMediator {
    fun getProfileScreen(name: String) : FragmentScreen
}