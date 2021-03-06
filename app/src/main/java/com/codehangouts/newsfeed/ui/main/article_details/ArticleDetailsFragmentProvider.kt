package com.codehangouts.newsfeed.ui.main.article_details

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ArticleDetailsFragmentProvider {
    @ContributesAndroidInjector(modules = [ArticleDetailsFragmentModule::class])
    abstract fun provideArticleDetailsFragmentFactory(): ArticleDetailsFragment
}