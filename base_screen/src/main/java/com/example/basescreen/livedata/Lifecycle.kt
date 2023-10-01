package com.example.basescreen.livedata

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData

fun <T : Any?, L : LiveData<Event<T>>> Fragment.observeEvent(liveData: L, body: (T) -> Unit) =
    liveData.observe(viewLifecycleOwner, EventObserver(body))