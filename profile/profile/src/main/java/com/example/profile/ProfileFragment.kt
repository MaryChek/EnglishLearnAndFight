package com.example.profile

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.example.basescreen.fragments.BaseScreenFragment
import com.example.core_api.providers.AppFacade
import com.example.core_api.providers.MainComponentProvider
import com.example.profile.databinding.FragmentProfileBinding
import com.example.profile.di.ProfileComponent
import com.example.profile.navigation.FromProfile
import com.example.profile.viewmodel.ProfileViewModel
import com.example.profile.viewmodel.ProfileViewModelFactory
import javax.inject.Inject

class ProfileFragment :
    BaseScreenFragment<Any, FragmentProfileBinding, FromProfile>(R.layout.fragment_profile) {

    override lateinit var viewModel: ProfileViewModel

    @Inject
    protected lateinit var viewModelFactory: ProfileViewModelFactory

    override fun onCreateViewBinding(view: View): FragmentProfileBinding =
        FragmentProfileBinding.bind(view)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ProfileComponent.create(
            (requireActivity().application as AppFacade).getFacade(),
            requireActivity() as MainComponentProvider
        ).inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ProfileViewModel::class.java)
    }

    override fun handleState(screenState: Any) {

    }
}