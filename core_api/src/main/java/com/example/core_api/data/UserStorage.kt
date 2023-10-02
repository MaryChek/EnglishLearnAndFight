package com.example.core_api.data

interface UserStorage {
    fun saveName(name: String)

    fun getName(): String?
}