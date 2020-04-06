package com.codehangouts.newsfeed.data.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.codehangouts.newsfeed.data.model.db.Article

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article)

    @Delete
    suspend fun delete(article: Article)

    @Query("SELECT * FROM favorites WHERE id = :id")
    suspend fun findById(id: Long): Article

    @Query("SELECT * FROM favorites")
    fun loadAll(): LiveData<List<Article>>
}