package com.example.core.data.storage

import com.example.core_api.data.UserStorage

class UserStorageImpl(private val prefs: Prefs) : UserStorage {
    override fun getName(): String? =
        prefs.getString(KEY_NAME)

    override fun saveName(name: String) =
        prefs.putString(KEY_NAME, name)

    companion object {
        private const val KEY_NAME = "key_name"
    }
}