package com.example.game.presentation.model

import android.content.res.Resources
import com.example.uikit.R

class GameResultScreenState(
    val resources: Resources,
    val currentResult: Int,
    val maxResult: Int? = null,
) {
    val tvResult =
        resources.getString(R.string.result_game, currentResult.toString(), maxResult?.toString())
}