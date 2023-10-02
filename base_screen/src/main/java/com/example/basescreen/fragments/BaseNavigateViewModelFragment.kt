package com.example.basescreen.fragments

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding
import com.example.basescreen.livedata.observeEvent
import com.example.basescreen.navigation.Action
import com.example.basescreen.viewmodels.BaseNavigateViewModel

abstract class BaseNavigateViewModelFragment<
        ViewBindingType : ViewBinding,
        NavigateType : Action>(
    @LayoutRes contentLayoutId: Int,
) : BaseFragmentWithViewBinding<ViewBindingType> (contentLayoutId) {

    abstract override val viewModel: BaseNavigateViewModel<NavigateType>?

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserves()
    }

    protected open fun onBackPressed(): Boolean {
        viewModel?.onBackPressed()
        return true
    }

    @CallSuper
    open fun setupObserves() {
        viewModel?.let { vm ->
            observeEvent(vm.navigate, ::handleNavigate)
            observeEvent(vm.action, ::handleNavigate)
        }
    }

    open fun handleNavigate(destination: NavigateType) {}
}