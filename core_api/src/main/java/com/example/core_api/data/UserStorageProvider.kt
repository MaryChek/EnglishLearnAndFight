package com.example.core_api.data

interface UserStorageProvider {
    fun provideUserStorage(): UserStorage
}