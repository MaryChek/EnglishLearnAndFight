package com.example.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.main.di.ActivityComponentHolder
import com.example.main.di.MainActivityComponent
import com.example.main.viewmodel.MainViewModel
import com.example.main.viewmodel.MainViewModelFactory
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.example.basescreen.livedata.observeEvent
import com.example.core_api.providers.AppFacade
import com.example.core_api.providers.MainComponentProvider
import com.example.login_api.LoginScreen
import com.example.main.navigation.FromMain
import com.example.profile_api.ProfileScreen
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main), MainComponentProvider {

    private val navigator: Navigator by lazy {
        AppNavigator(this, R.id.fragment_container)
    }

    @Inject
    protected lateinit var router: Router

    @Inject
    protected lateinit var navigatorHolder: NavigatorHolder

    protected lateinit var viewModel: MainViewModel

    @Inject
    protected lateinit var viewModelFactory: MainViewModelFactory

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
        setupObservers()
        if (savedInstanceState == null) {
            viewModel.init()
        }
    }

    private fun setupObservers() =
        observeEvent(viewModel.navigate, ::handleNavigation)

    private fun handleNavigation(destination: FromMain) {
        when (destination) {
            is FromMain.GoTo.NewRootScreen.Login ->
                router.newRootScreen(LoginScreen.Login)
            is FromMain.GoTo.NewRootScreen.Profile ->
                router.newRootScreen(ProfileScreen.Profile(destination.name))
            is FromMain.GoTo.Back ->
                finish()
        }
    }

    private fun initializeComponent() {
        getActivityComponent().inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
    }

    override fun getActivityComponent(): MainActivityComponent =
        ActivityComponentHolder.getActivityComponent((application as AppFacade).getFacade())

    override fun onDestroy() {
        super.onDestroy()
        if (!isChangingConfigurations) {
            ActivityComponentHolder.clear()
        }
    }
}