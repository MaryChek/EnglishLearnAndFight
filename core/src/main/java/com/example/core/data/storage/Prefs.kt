package com.example.core.data.storage

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class Prefs(context: Context) {
    private val sp: SharedPreferences = context.getSharedPreferences(NAME_STORAGE, Context.MODE_PRIVATE)

    fun putString(key: String, value: String?) =
        sp.edit(true) {
            putString(key, value)
        }

    fun getString(key: String, default: String? = null) =
        sp.getString(key, default)

    companion object {
        private const val NAME_STORAGE = "settings"
    }
}