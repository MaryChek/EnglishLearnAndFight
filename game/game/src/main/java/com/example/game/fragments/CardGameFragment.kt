package com.example.game.fragments

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
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
import javax.inject.Inject

class CardGameFragment : BaseScreenFragment<CardGameScreenState, FragmentCardGameBinding, FromGame>(
    R.layout.fragment_card_game
) {
    override lateinit var viewModel: GameViewModel

    @Inject
    protected lateinit var viewModelFactory: CardGameViewModelFactory

    lateinit var backAnim: AnimatorSet
    lateinit var frontAnim: AnimatorSet

    var isStart = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GameComponent.create(requireActivity() as MainComponentProvider)
            .inject(this)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(GameViewModel::class.java)
    }

    override fun handleState(screenState: CardGameScreenState) {}

    override fun onCreateViewBinding(view: View): FragmentCardGameBinding =
        FragmentCardGameBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAnimation()
    }

    private fun setupAnimation() {
        val appContext = requireActivity().applicationContext
        val scale: Float = appContext.resources.displayMetrics.density
        binding?.apply {
            cardFront.cameraDistance = 8000 * scale
            cardBack.cameraDistance = 8000 * scale

            frontAnim = AnimatorInflater
                .loadAnimator(appContext, R.animator.front_card_animator) as AnimatorSet

            backAnim = AnimatorInflater
                .loadAnimator(appContext, R.animator.back_card_animator) as AnimatorSet

//            btnFight.setOnClickListener {
//                if (isStart) {
//                    frontAnim.setTarget(cardFront)
//                    backAnim.setTarget(cardBack)
//                    frontAnim.start()
//                    backAnim.start()
//                    isStart = false
//                }
//            }
        }
    }
}