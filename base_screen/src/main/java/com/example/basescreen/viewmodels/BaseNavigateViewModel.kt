package com.example.basescreen.viewmodels

import android.util.Log
import androidx.annotation.CallSuper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.basescreen.livedata.Event
import com.example.basescreen.navigation.Action

abstract class BaseNavigateViewModel<NavigateType : Action> : ViewModel() {
    val navigate = MutableLiveData<Event<NavigateType>>()
    val action = MutableLiveData<Event<NavigateType>>()

    @CallSuper
    protected open fun handleNavigate(destination: NavigateType) {
        when (destination) {
            is Action.GoTo ->
                navigate.postValue(Event(destination))
            is Action.Command ->
                action.postValue(Event(destination))
            else ->
                Log.d(
                    this::class.java.canonicalName,
                    "Incorrect action type: ${destination::class.java} " +
                            "must be expended Action.Navigate or Action.Command"
                )
        }
    }

    open fun onBackPressed() = Unit
}