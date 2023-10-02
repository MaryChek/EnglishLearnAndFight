package com.example.login.models

class LoginScreenState(val name: String) {
    val isContinueEnable: Boolean = name.isNotBlank()
}