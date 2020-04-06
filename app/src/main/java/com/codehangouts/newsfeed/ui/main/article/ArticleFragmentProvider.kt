package com.codehangouts.newsfeed.ui.main.article

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ArticleFragmentProvider {
    @ContributesAndroidInjector(modules = [ArticleFragmentModule::class])
    abstract fun provideArticleFragmentFactory(): ArticleFragment
}