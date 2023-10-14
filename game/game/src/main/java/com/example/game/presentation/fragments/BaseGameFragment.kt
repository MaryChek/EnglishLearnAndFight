package com.example.game.presentation.fragments

import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding
import com.example.basescreen.fragments.BaseScreenFragment
import com.example.basescreen.navigation.Action
import com.example.core_api.providers.AppFacade
import com.example.game.di.BaseGameComponentHolder

abstract class BaseGameFragment<
        ScreenState : Any,
        ViewBindingType : ViewBinding,
        NavigateType : Action>(
    @LayoutRes contentLayoutId: Int,
) : BaseScreenFragment<ScreenState, ViewBindingType, NavigateType>(contentLayoutId) {

    protected fun getBaseGameComponent(): com.example.game.di.BaseGameComponent =
        BaseGameComponentHolder.getComponent(requireActivity().application as AppFacade)
}