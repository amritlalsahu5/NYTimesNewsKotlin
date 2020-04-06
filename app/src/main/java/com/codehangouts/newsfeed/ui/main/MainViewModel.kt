package com.codehangouts.newsfeed.ui.main

import android.app.Application
import com.codehangouts.newsfeed.data.AppDataManager
import com.codehangouts.newsfeed.ui.base.BaseViewModel

class MainViewModel(
    application: Application,
    appDataManager: AppDataManager
) : BaseViewModel<Any>(application, appDataManager)