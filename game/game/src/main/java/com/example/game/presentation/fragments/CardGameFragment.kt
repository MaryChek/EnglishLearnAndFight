package com.example.game.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProviders
import com.example.basescreen.models.Status
import com.example.core_api.providers.MainComponentProvider
import com.example.game.R
import com.example.game.databinding.FragmentCardGameBinding
import com.example.game.di.BaseGameComponentHolder
import com.example.game.di.CardGameComponent
import com.example.game.presentation.model.CardGameScreenState
import com.example.game.presentation.navigation.FromCardGame
import com.example.game.presentation.routers.CardGameRouter
import com.example.game.presentation.viewmodel.CardGameViewModelFactory
import com.example.game.presentation.viewmodel.GameViewModel
import com.example.uikit.views.onChangeTextListener
import javax.inject.Inject

class CardGameFragment :
    BaseGameFragment<CardGameScreenState, FragmentCardGameBinding, FromCardGame>(
        R.layout.fragment_card_game
    ) {
    override lateinit var viewModel: GameViewModel

    @Inject
    protected lateinit var viewModelFactory: CardGameViewModelFactory

    @Inject
    protected lateinit var router: CardGameRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CardGameComponent.create(
            requireActivity() as MainComponentProvider, getBaseGameComponent()
        )
            .inject(this)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(GameViewModel::class.java)
    }

    override fun onCreateViewBinding(view: View): FragmentCardGameBinding =
        FragmentCardGameBinding.bind(view)

    override fun handleStatus(status: Status) {
        when (status) {
            Status.LOADING -> handleProgressStatus(true)
            Status.SUCCESS,
            Status.END,
            Status.ERROR -> handleProgressStatus(false)
        }
    }

    private fun handleProgressStatus(inProgress: Boolean) {
        binding?.progressBar?.isVisible = inProgress
        binding?.btnNext?.isEnabled = !inProgress
//        binding?.btnSubmit?.isEnabled = !inProgress
    }

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
            it.isEnabled = false
            it.postDelayed(
                {
                    binding?.btnNext?.isEnabled = true
                },
                DELAY_TO_UPDATE_SECONDS
            )
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
        binding?.tvWordCount?.text = screenState.tvWordCount
        if (screenState.shouldUpdateWord) {
            binding?.cardGame?.updateCard(screenState.currentWord)
        }
    }

    override fun handleNavigate(destination: FromCardGame) {
        if (destination is FromCardGame.GoTo.Back) {
            BaseGameComponentHolder.clear()
        }
        when (destination) {
            is FromCardGame.GoTo -> router.goTo(destination)
            is FromCardGame.Command.ClearAnswer ->
                binding?.etAnswer?.apply {
                    setQuery("", false)
                    clearFocus()
                }
        }
    }

    companion object {
        private const val DELAY_TO_UPDATE_SECONDS = 900L
    }
}