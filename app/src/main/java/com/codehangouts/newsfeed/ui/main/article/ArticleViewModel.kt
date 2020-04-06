package com.codehangouts.newsfeed.ui.main.article

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.codehangouts.newsfeed.data.AppDataManager
import com.codehangouts.newsfeed.data.model.Result
import com.codehangouts.newsfeed.data.model.api.ArticlesResponse
import com.codehangouts.newsfeed.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class ArticleViewModel(
    application: Application,
    appDataManager: AppDataManager
) : BaseViewModel<ArticleNavigator>(application, appDataManager) {
    private val articlesLiveData: MutableLiveData<List<ArticleDataItem>> = MutableLiveData()

    fun fetchArticles(period: Int) {
        launch {
            setIsLoading(true)
            when (val result = appDataManager.getApiRepository().getArticles(period)) {
                is Result.Success<ArticlesResponse> -> {
                    result.data.articles?.let { mapArticlesDataItem(it) }
                    setIsLoading(false)
                }
                is Result.Error -> {
                    setIsLoading(false)
                    navigator?.handleError(result.message)
                }
            }
        }
    }

    val articlesLiveDataLiveData: LiveData<List<ArticleDataItem>>
        get() = articlesLiveData

    init {
        fetchArticles(7)
    }

    private fun mapArticlesDataItem(articles: List<ArticlesResponse.Article>) {
        articlesLiveData.value = articles.map {
            ArticleDataItem(
                it.id
                ,
                if (!it.media.isNullOrEmpty()) it.media?.get(0)?.mediaMetaData?.get(2)?.url else ""
                ,
                it.title
                ,
                it.byline
                ,
                it.abstractX
                ,
                it.publishedDate
                ,
                it.url,
                if (!it.media.isNullOrEmpty()) it.media?.get(0)?.mediaMetaData?.get(1)?.url else ""
            )
        }
    }
}