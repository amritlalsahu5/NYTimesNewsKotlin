package com.codehangouts.newsfeed.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codehangouts.newsfeed.data.local.db.dao.ArticleDao
import com.codehangouts.newsfeed.data.model.db.Article

@Database(
    entities = [Article::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}