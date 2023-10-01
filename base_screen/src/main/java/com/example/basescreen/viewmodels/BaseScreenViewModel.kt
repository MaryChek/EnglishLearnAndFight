package com.example.basescreen.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.basescreen.livedata.Event

abstract class BaseScreenViewModel<ScreenState: Any>(initModel: ScreenState) : ViewModel() {

    val screenState = MutableLiveData<Event<ScreenState>>()
    protected var model: ScreenState = initModel

    protected fun handleScreenState() {
        screenState.postValue(Event(model))
    }
}