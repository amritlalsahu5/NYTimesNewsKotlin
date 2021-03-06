package com.codehangouts.newsfeed.data.local.db

import androidx.lifecycle.LiveData
import com.codehangouts.newsfeed.data.model.db.Article
import com.codehangouts.newsfeed.data.model.Result

interface DbDataSource {
    suspend fun insertArticle(article: Article)
    suspend fun deleteArticle(article: Article)
    suspend fun findById(id: Long): Result<Article>
    fun allArticles(): LiveData<List<Article>>
}