package com.codehangouts.newsfeed.data.remote

import com.codehangouts.newsfeed.data.model.Result
import com.codehangouts.newsfeed.data.model.api.ArticlesResponse

interface ApiDataSource {
    suspend fun getArticles(period: Int): Result<ArticlesResponse>
}