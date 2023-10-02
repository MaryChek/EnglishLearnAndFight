package com.example.basescreen.navigation

interface BaseRouter<Destination : Action.GoTo> {
    fun goTo(destination: Destination)
}