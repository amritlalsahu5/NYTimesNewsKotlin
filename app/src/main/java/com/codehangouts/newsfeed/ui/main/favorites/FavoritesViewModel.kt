package com.codehangouts.newsfeed.ui.main.favorites

import android.app.Application
import androidx.lifecycle.LiveData
import com.codehangouts.newsfeed.data.AppDataManager
import com.codehangouts.newsfeed.data.model.db.Article
import com.codehangouts.newsfeed.ui.base.BaseViewModel

class FavoritesViewModel(
    application: Application,
    appDataManager: AppDataManager
) : BaseViewModel<FavoritesNavigator>(application, appDataManager) {
    val articlesLiveDataLiveData: LiveData<List<Article>> = appDataManager.getDbRepository().allArticles()
}