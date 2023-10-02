package com.example.basescreen.fragments

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseFragmentWithViewBinding<ViewBindingType : ViewBinding>(
    @LayoutRes contentLayoutId: Int,
) : Fragment(contentLayoutId) {
    protected var binding: ViewBindingType? = null

    protected open val viewModel: ViewModel? = null

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = onCreateViewBinding(view)
    }

    protected abstract fun onCreateViewBinding(view: View): ViewBindingType

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}