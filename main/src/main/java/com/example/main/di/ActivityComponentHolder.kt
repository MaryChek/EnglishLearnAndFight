package com.example.main.di

object ActivityComponentHolder {
    private var activityComponent: MainActivityComponent? = null

    fun getActivityComponent(): MainActivityComponent =
        activityComponent ?: MainActivityComponent.buildMainComponent()
            .also { newComponent ->
                activityComponent = newComponent
            }

    fun clear() {
        activityComponent = null
    }
}