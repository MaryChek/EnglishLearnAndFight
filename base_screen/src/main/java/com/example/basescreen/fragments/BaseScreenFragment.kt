package com.example.basescreen.fragments

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.basescreen.livedata.observeEvent
import com.example.basescreen.viewmodels.BaseScreenViewModel

abstract class BaseScreenFragment<ViewBindingType : ViewBinding, ScreenState : Any>(
    @LayoutRes contentLayoutId: Int,
) : Fragment(contentLayoutId) {
    protected var binding: ViewBindingType? = null

    abstract val viewModel: BaseScreenViewModel<ScreenState>?

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

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = onCreateViewBinding(view)
    }

    protected abstract fun onCreateViewBinding(view: View): ViewBindingType

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}