package com.example.englishlearnandfight

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.englishlearnandfight.R
import com.example.login.LoginFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_Main)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            addRootFragment()
        }
    }

    private fun addRootFragment() {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, LoginFragment())
            addToBackStack(null)
        }.commit()
    }
}