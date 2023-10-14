package com.example.basescreen.viewmodels

import androidx.lifecycle.MutableLiveData
import com.example.basescreen.livedata.Event
import com.example.basescreen.models.Status
import com.example.basescreen.navigation.Action

abstract class BaseScreenViewModel<ScreenState : Any, NavigateType : Action>(initModel: ScreenState) :
    BaseNavigateViewModel<NavigateType>() {

    val screenState = MutableLiveData<Event<ScreenState>>()
    val mainStatus = MutableLiveData<Event<Status>>()
    protected var model: ScreenState = initModel


    protected fun handleScreenState() {
        screenState.postValue(Event(model))
    }

    protected fun handleStatue(state: Status) {
        mainStatus.postValue(Event(state))
    }
}