package com.example.game.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.example.basescreen.fragments.BaseScreenFragment
import com.example.core_api.providers.MainComponentProvider
import com.example.game.R
import com.example.game.databinding.FragmentCardGameBinding
import com.example.game.di.GameComponent
import com.example.game.model.CardGameScreenState
import com.example.game.navigation.FromGame
import com.example.game.viewmodel.CardGameViewModelFactory
import com.example.game.viewmodel.GameViewModel
import com.example.uikit.views.onChangeTextListener
import javax.inject.Inject

class CardGameFragment : BaseScreenFragment<CardGameScreenState, FragmentCardGameBinding, FromGame>(
    R.layout.fragment_card_game
) {
    override lateinit var viewModel: GameViewModel

    @Inject
    protected lateinit var viewModelFactory: CardGameViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GameComponent.create(requireActivity() as MainComponentProvider)
            .inject(this)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(GameViewModel::class.java)
    }

    override fun onCreateViewBinding(view: View): FragmentCardGameBinding =
        FragmentCardGameBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        viewModel.onViewCreated()
    }

    private fun setupClickListeners() {
        binding?.btnNext?.setOnClickListener {
            viewModel.onNextClick()
        }
        binding?.btnSubmit?.setOnClickListener {
            viewModel.onAnswerSubmitClick()
        }
        binding?.etAnswer?.onChangeTextListener {
            viewModel.onAnswerChange(it)
        }
    }

    override fun handleState(screenState: CardGameScreenState) {
        binding?.btnSubmit?.isEnabled = screenState.isBtnSubmitEnable
        binding?.cardGame?.updateCard(screenState.currentWord)
    }
}