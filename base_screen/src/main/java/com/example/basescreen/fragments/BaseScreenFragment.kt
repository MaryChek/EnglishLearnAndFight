package com.example.basescreen.fragments

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding
import com.example.basescreen.livedata.observeEvent
import com.example.basescreen.navigation.Action
import com.example.basescreen.viewmodels.BaseScreenViewModel

abstract class BaseScreenFragment<
        ScreenState : Any,
        ViewBindingType : ViewBinding,
        NavigateType : Action>(
    @LayoutRes contentLayoutId: Int,
) : BaseNavigateViewModelFragment<ViewBindingType, NavigateType>(contentLayoutId) {
    abstract override val viewModel: BaseScreenViewModel<ScreenState, NavigateType>?

    @CallSuper
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeBaseLiveData()
    }

    protected open fun observeBaseLiveData() {
        viewModel?.let { vm ->
            observeEvent(vm.screenState, ::handleState)
        }
    }

    protected abstract fun handleState(screenState: ScreenState)
}