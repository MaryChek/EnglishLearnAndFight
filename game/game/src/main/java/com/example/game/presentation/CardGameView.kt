package com.example.game.presentation

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.game.R
import com.example.game.databinding.CardGameBinding

class CardGameView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var backAnim: AnimatorSet
    private lateinit var frontAnim: AnimatorSet

    private var isFront = true
    private var isDefaultSet = true

    private var binding: CardGameBinding

    init {
        binding = CardGameBinding.inflate(LayoutInflater.from(context), this, true)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setupAnimation()
    }

    private fun setupAnimation() {
        val scale: Float = context.resources.displayMetrics.density
        binding.cardFront.cameraDistance = 8000 * scale
        binding.cardBack.cameraDistance = 8000 * scale

        frontAnim = AnimatorInflater
            .loadAnimator(context, R.animator.front_card_animator) as AnimatorSet

        backAnim = AnimatorInflater
            .loadAnimator(context, R.animator.back_card_animator) as AnimatorSet
    }

    fun updateCard(text: String) {
        if (!isDefaultSet) {
            setTextAndRotation(text)
        } else {
            binding.textFront.text = text
            isDefaultSet = false
        }
    }

    private fun setTextAndRotation(text: String) {
        if (isFront) {
            binding.textBack.text = text
            frontAnim.setTarget(binding.cardFront)
            backAnim.setTarget(binding.cardBack)
            frontAnim.start()
            backAnim.start()
        } else {
            binding.textFront.text = text
            frontAnim.setTarget(binding.cardBack)
            backAnim.setTarget(binding.cardFront)
            frontAnim.start()
            backAnim.start()
        }
        isFront = !isFront
    }
}