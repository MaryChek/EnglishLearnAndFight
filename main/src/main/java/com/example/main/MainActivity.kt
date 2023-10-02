package com.example.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.login.LoginFragment
import com.example.main.di.ActivityComponentHolder
import com.example.main.di.MainActivityComponent
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.Creator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val navigator: Navigator by lazy {
        AppNavigator(this, R.id.fragment_container)
    }

    @Inject
    protected lateinit var router: Router

    @Inject
    protected lateinit var navigatorHolder: NavigatorHolder

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_Main)
        super.onCreate(savedInstanceState)
        initializeComponent()
        if (savedInstanceState == null) {
            addRootFragment()
        }
    }

    private fun initializeComponent() {
        getActivityComponent().inject(this)
    }

    private fun addRootFragment() {
        router.newRootScreen(
            FragmentScreen(fragmentCreator = object : Creator<FragmentFactory, Fragment> {
                override fun create(argument: FragmentFactory): Fragment =
                    LoginFragment()
            })
        )
//        supportFragmentManager.beginTransaction().apply {
//            replace(R.id.fragment_container, LoginFragment())
//            addToBackStack(null)
//        }.commit()
    }

    fun getActivityComponent(): MainActivityComponent =
        ActivityComponentHolder.getActivityComponent()

    override fun onDestroy() {
        super.onDestroy()
        if (!isChangingConfigurations) {
            ActivityComponentHolder.clear()
        }
    }
}