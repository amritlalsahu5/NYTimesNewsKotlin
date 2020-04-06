package com.codehangouts.newsfeed.ui.main.favorites

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FavoritesFragmentProvider {
    @ContributesAndroidInjector(modules = [FavoritesFragmentModule::class])
    abstract fun provideFavoritesFragmentFactory(): FavoritesFragment
}