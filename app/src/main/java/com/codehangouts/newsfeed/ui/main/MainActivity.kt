package com.codehangouts.newsfeed.ui.main

import androidx.lifecycle.ViewModelProvider
import com.codehangouts.newsfeed.BR
import com.codehangouts.newsfeed.R
import com.codehangouts.newsfeed.ViewModelProviderFactory
import com.codehangouts.newsfeed.databinding.ActivityMainBinding
import com.codehangouts.newsfeed.ui.base.BaseActivity
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(),
    HasAndroidInjector {
    @Inject
    lateinit var factory: ViewModelProviderFactory

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.activity_main

    override val viewModel: MainViewModel
        get() = ViewModelProvider(this, factory).get(MainViewModel::class.java)
}