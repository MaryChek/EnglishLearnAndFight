package com.example.game

import android.view.View
import com.example.basescreen.fragments.BaseScreenFragment
import com.example.game.databinding.FragmentStartGameBinding
import com.example.game.model.StartGameScreenState
import com.example.game.navigation.FromStartGame
import com.example.game.viewmodel.StartGameViewModel

class StartGameFragment : BaseScreenFragment<StartGameScreenState, FragmentStartGameBinding, FromStartGame>(
    R.layout.fragment_start_game
) {
    override lateinit var viewModel: StartGameViewModel

    override fun handleState(screenState: StartGameScreenState) {}

    override fun onCreateViewBinding(view: View): FragmentStartGameBinding =
        FragmentStartGameBinding.bind(view)
}