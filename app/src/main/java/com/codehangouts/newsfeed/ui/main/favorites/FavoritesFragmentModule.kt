package com.codehangouts.newsfeed.ui.main.favorites

import dagger.Module
import dagger.Provides
import java.util.*

@Module
class FavoritesFragmentModule {
    @Provides
    fun provideFavoritesAdapter(): FavoritesAdapter {
        return FavoritesAdapter(ArrayList())
    }
}