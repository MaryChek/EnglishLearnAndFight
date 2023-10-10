package com.example.game.data.model

import android.util.Log

sealed class Result<out T>(val status: Status, val data: T?) {

    class Success<T>(data: T) : Result<T>(Status.SUCCESS, data)

    class Error<T>(tag: String, throwable: Throwable? = null) : Result<T>(Status.ERROR, null) {
        init {
            Log.w(tag, throwable?.message ?: "", throwable)
        }

        val exceptionMessage: String? = throwable?.message
    }

    class Loading<T> : Result<T>(Status.LOADING, null)

    class End<T> : Result<T>(Status.END, null)

    companion object {
        enum class Status {
            SUCCESS,
            ERROR,
            LOADING,
            END
        }
    }
}
