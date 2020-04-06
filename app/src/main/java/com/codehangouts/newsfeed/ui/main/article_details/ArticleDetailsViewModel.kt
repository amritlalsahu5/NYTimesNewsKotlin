package com.codehangouts.newsfeed.ui.main.article_details

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.codehangouts.newsfeed.data.AppDataManager
import com.codehangouts.newsfeed.data.model.Result
import com.codehangouts.newsfeed.data.model.db.Article
import com.codehangouts.newsfeed.ui.base.BaseViewModel
import com.codehangouts.newsfeed.ui.main.article.ArticleDataItem
import kotlinx.coroutines.launch

class ArticleDetailsViewModel(
    application: Application,
    appDataManager: AppDataManager
) : BaseViewModel<ArticleDetailsNavigator>(application, appDataManager) {
    private val isFavorite: MutableLiveData<Boolean> = MutableLiveData()

    private fun insertArticle(articleDataItem: ArticleDataItem) {
        launch {
            appDataManager.getDbRepository().insertArticle(
                Article(
                    articleDataItem.id
                    , articleDataItem.imageUrl
                    , articleDataItem.title
                    , articleDataItem.byline
                    , articleDataItem.abstractX
                    , articleDataItem.publishedDate
                    , articleDataItem.url
                    , articleDataItem.coverImageUrl
                )
            )
            isFavorite.value = true
        }
    }

    private fun deleteArticle(articleDataItem: ArticleDataItem) {
        launch {
            appDataManager.getDbRepository().deleteArticle(
                Article(
                    articleDataItem.id
                    , articleDataItem.imageUrl
                    , articleDataItem.title
                    , articleDataItem.byline
                    , articleDataItem.abstractX
                    , articleDataItem.publishedDate
                    , articleDataItem.url
                    , articleDataItem.coverImageUrl
                )
            )
            isFavorite.value = false
        }
    }

    fun findById(id: Long) {
        launch {
            when (appDataManager.getDbRepository().findById(id)) {
                is Result.Success<Article> -> {
                    isFavorite.value = true
                }
                is Result.Error -> {
                    isFavorite.value = false
                }
            }
        }
    }

    fun onFavClick(
        isFavorite: Boolean,
        articleDataItem: ArticleDataItem
    ) {
        if (isFavorite) deleteArticle(articleDataItem) else insertArticle(articleDataItem)
    }

    fun getIsFavorite(): LiveData<Boolean> {
        return isFavorite
    }

}