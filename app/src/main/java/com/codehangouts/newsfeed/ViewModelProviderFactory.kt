package com.codehangouts.newsfeed

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.codehangouts.newsfeed.data.AppDataManager
import com.codehangouts.newsfeed.ui.main.MainViewModel
import com.codehangouts.newsfeed.ui.main.article.ArticleViewModel
import com.codehangouts.newsfeed.ui.main.article_details.ArticleDetailsViewModel
import com.codehangouts.newsfeed.ui.main.favorites.FavoritesViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelProviderFactory @Inject constructor(
    private val application: Application,
    private val appDataManager: AppDataManager
) : NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(
                    application,
                    appDataManager
                ) as T
            }
            modelClass.isAssignableFrom(ArticleViewModel::class.java) -> {
                ArticleViewModel(
                    application,
                    appDataManager
                ) as T
            }
            modelClass.isAssignableFrom(ArticleDetailsViewModel::class.java) -> {
                ArticleDetailsViewModel(
                    application,
                    appDataManager
                ) as T
            }
            modelClass.isAssignableFrom(FavoritesViewModel::class.java) -> {
                FavoritesViewModel(
                    application,
                    appDataManager
                ) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}