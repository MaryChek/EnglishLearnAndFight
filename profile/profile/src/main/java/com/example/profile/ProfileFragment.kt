package com.example.profile

import android.os.Bundle
import android.view.View
import com.example.basescreen.fragments.BaseScreenFragment
import com.example.profile.databinding.FragmentProfileBinding
import com.example.profile.navigation.FromProfile
import com.example.profile.viewmodel.ProfileViewModel

class ProfileFragment :
    BaseScreenFragment<Any, FragmentProfileBinding, FromProfile>(R.layout.fragment_profile) {

    override lateinit var viewModel: ProfileViewModel

    override fun onCreateViewBinding(view: View): FragmentProfileBinding =
        FragmentProfileBinding.bind(view)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //viewModel =
    }

    override fun handleState(screenState: Any) {

    }
}