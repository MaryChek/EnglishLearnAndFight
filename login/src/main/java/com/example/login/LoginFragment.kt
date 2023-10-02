package com.example.login

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.example.basescreen.fragments.BaseScreenFragment
import com.example.core_api.providers.AppFacade
import com.example.login.databinding.FragmentLoginBinding
import com.example.login.di.LoginComponent
import com.example.login.models.LoginScreenState
import com.example.login.viewmodel.LoginViewModel
import com.example.login.viewmodel.LoginViewModelFactory
import com.example.uikit.views.onChangeTextListener
import javax.inject.Inject

class LoginFragment :
    BaseScreenFragment<FragmentLoginBinding, LoginScreenState>(R.layout.fragment_login) {

    override lateinit var viewModel: LoginViewModel

    @Inject
    lateinit var viewModelFactory: LoginViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LoginComponent.create((requireActivity().application as AppFacade).getFacade())
            .inject(this)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
    }

    override fun onCreateViewBinding(view: View): FragmentLoginBinding =
        FragmentLoginBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        binding?.btnContinue?.setOnClickListener {
            viewModel.onContinueClick()
        }
        binding?.etLoginName?.onChangeTextListener { name ->
            viewModel.onNameChange(name)
        }
    }

    override fun handleState(screenState: LoginScreenState) {
        binding?.btnContinue?.isEnabled = screenState.isContinueEnable
    }
}