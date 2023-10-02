package com.example.profile

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProviders
import com.example.basescreen.fragments.BaseScreenFragment
import com.example.core_api.providers.AppFacade
import com.example.core_api.providers.MainComponentProvider
import com.example.profile.databinding.FragmentProfileBinding
import com.example.profile.di.ProfileComponent
import com.example.profile.navigation.FromProfile
import com.example.profile.navigation.ProfileRouter
import com.example.profile.viewmodel.ProfileViewModel
import com.example.profile.viewmodel.ProfileViewModelFactory
import javax.inject.Inject

class ProfileFragment :
    BaseScreenFragment<Any, FragmentProfileBinding, FromProfile>(R.layout.fragment_profile) {

    @Inject
    lateinit var router: ProfileRouter

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupName()
        setupClickListeners()
    }

    private fun setupName() {
        arguments?.getString(PROFILE_NAME_ARG)?.let { name ->
            binding?.tvName?.text = name
        }
    }

    private fun setupClickListeners() {
        binding?.btnTrain?.setOnClickListener {
            viewModel.onStartTrainClick()
        }
    }

    override fun handleNavigate(destination: FromProfile) {
        when (destination) {
            is FromProfile.GoTo -> router.goTo(destination)
        }
    }

    override fun handleState(screenState: Any) {}

    companion object {
        private const val PROFILE_NAME_ARG = "profile_name_arg"

        fun newInstance(name: String) : ProfileFragment =
            ProfileFragment().apply {
                arguments = bundleOf(
                    PROFILE_NAME_ARG to name,
                )
            }
    }
}