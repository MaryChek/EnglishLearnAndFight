package com.example.game

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.example.basescreen.fragments.BaseScreenFragment
import com.example.core_api.providers.MainComponentProvider
import com.example.game.databinding.FragmentStartGameBinding
import com.example.game.di.StartGameComponent
import com.example.game.model.StartGameScreenState
import com.example.game.navigation.FromStartGame
import com.example.game.viewmodel.StartGameViewModel
import com.example.game.viewmodel.StartGameViewModelFactory
import javax.inject.Inject

class StartGameFragment :
    BaseScreenFragment<StartGameScreenState, FragmentStartGameBinding, FromStartGame>(
        R.layout.fragment_start_game
    ) {
    override lateinit var viewModel: StartGameViewModel

    @Inject
    protected lateinit var viewModelFactory: StartGameViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StartGameComponent.create(requireActivity() as MainComponentProvider)
            .inject(this)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(StartGameViewModel::class.java)
    }

    override fun handleState(screenState: StartGameScreenState) {}

    override fun onCreateViewBinding(view: View): FragmentStartGameBinding =
        FragmentStartGameBinding.bind(view)
}