package com.example.game.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.example.basescreen.fragments.BaseScreenFragment
import com.example.core_api.providers.MainComponentProvider
import com.example.game.R
import com.example.game.databinding.FragmentCardGameBinding
import com.example.game.di.GameComponent
import com.example.game.presentation.model.CardGameScreenState
import com.example.game.presentation.navigation.FromCardGame
import com.example.game.presentation.routers.CardGameRouter
import com.example.game.presentation.viewmodel.CardGameViewModelFactory
import com.example.game.presentation.viewmodel.GameViewModel
import com.example.uikit.views.onChangeTextListener
import javax.inject.Inject

class CardGameFragment : BaseScreenFragment<CardGameScreenState, FragmentCardGameBinding, FromCardGame>(
    R.layout.fragment_card_game
) {
    override lateinit var viewModel: GameViewModel

    @Inject
    protected lateinit var viewModelFactory: CardGameViewModelFactory

    @Inject
    protected lateinit var router: CardGameRouter

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
        binding?.toolbar?.setNavigationOnClickListener {
            viewModel.onToolbarClick()
        }
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

    override fun handleNavigate(destination: FromCardGame) {
        when (destination) {
            is FromCardGame.GoTo -> router.goTo(destination)
        }
    }
}