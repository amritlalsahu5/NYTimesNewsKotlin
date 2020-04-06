package com.codehangouts.newsfeed.di.builder

import com.codehangouts.newsfeed.ui.main.MainActivity
import com.codehangouts.newsfeed.ui.main.MainActivityModule
import com.codehangouts.newsfeed.ui.main.article.ArticleFragmentProvider
import com.codehangouts.newsfeed.ui.main.article_details.ArticleDetailsFragmentProvider
import com.codehangouts.newsfeed.ui.main.favorites.FavoritesFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [MainActivityModule::class, ArticleFragmentProvider::class, ArticleDetailsFragmentProvider::class, FavoritesFragmentProvider::class])
    abstract fun bindMainActivity(): MainActivity
}