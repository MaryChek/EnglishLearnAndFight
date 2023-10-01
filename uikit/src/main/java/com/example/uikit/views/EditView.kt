package com.example.uikit.views

import androidx.appcompat.widget.SearchView

fun SearchView.onChangeTextListener(onTextChange: (text: String) -> Unit) {
    setOnQueryTextListener(
        object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(enteredText: String?): Boolean {
                if (enteredText != null) {
                    onTextChange(enteredText)
                }
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                hideKeyboard()
                return true
            }
        })
}