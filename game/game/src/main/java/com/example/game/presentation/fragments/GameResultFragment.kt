package com.example.game.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProviders
import com.example.basescreen.fragments.BaseScreenFragment
import com.example.core_api.providers.MainComponentProvider
import com.example.game.R
import com.example.game.databinding.FragmentGameResultBinding
import com.example.game.di.BaseGameComponent
import com.example.game.di.BaseGameComponentHolder
import com.example.game.di.GameResultComponent
import com.example.game.presentation.model.GameResultScreenState
import com.example.game.presentation.navigation.FromCardGame
import com.example.game.presentation.navigation.FromGameResult
import com.example.game.presentation.routers.GameResultRouter
import com.example.game.presentation.viewmodel.GameResultViewModel
import com.example.game.presentation.viewmodel.GameResultViewModelFactory
import javax.inject.Inject

class GameResultFragment :
    BaseScreenFragment<GameResultScreenState, FragmentGameResultBinding, FromGameResult>(
        R.layout.fragment_game_result
    ) {
    override lateinit var viewModel: GameResultViewModel

    @Inject
    protected lateinit var viewModelFactory: GameResultViewModelFactory

    @Inject
    protected lateinit var router: GameResultRouter

    override fun onCreateViewBinding(view: View): FragmentGameResultBinding =
        FragmentGameResultBinding.bind(view)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initComponentAndViewModel((arguments?.getInt(ARG_RESULT) ?: 0))
    }

    private fun initComponentAndViewModel(result: Int) {
        GameResultComponent
            .create(
                requireActivity() as MainComponentProvider,
                getBaseGameComponent(),
                result,
                resources
            )
            .inject(this)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(GameResultViewModel::class.java)
    }

    private fun getBaseGameComponent(): BaseGameComponent =
        BaseGameComponentHolder.getComponent()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        viewModel.onViewCreated()
    }

    private fun setupViews() {
        binding?.btnRetry?.setOnClickListener {
            viewModel.onRetryClick()
        }
        binding?.btnCloseGame?.setOnClickListener {
            viewModel.onCloseGameClick()
        }
    }

    override fun handleState(screenState: GameResultScreenState) {
        binding?.tvResult?.text = screenState.tvResult
    }

    override fun handleNavigate(destination: FromGameResult) {
        if (destination is FromGameResult.GoTo) {
            router.goTo(destination)
            BaseGameComponentHolder.clear()
        }
    }

    companion object {
        private const val ARG_RESULT = "arg_result"
        private const val DEFAULT_RES = "0"

        fun newInstance(result: Int): GameResultFragment =
            GameResultFragment().apply {
                arguments = bundleOf(
                    ARG_RESULT to result
                )
            }
    }
}