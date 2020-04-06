package com.codehangouts.newsfeed.ui.main.favorites

import androidx.databinding.ObservableField
import com.codehangouts.newsfeed.data.model.db.Article
import com.codehangouts.newsfeed.ui.base.BaseItemListener

class FavoritesItemViewModel(
    private val article: Article,
    private val mListener: FavoritesItemViewModelListener
) {
    val imageUrl: ObservableField<String?> = ObservableField(article.imageUrl)
    val title: ObservableField<String?> = ObservableField(article.title)
    val byline: ObservableField<String?> = ObservableField(article.byline)
    val publishedDate: ObservableField<String?> = ObservableField(article.publishedDate)

    fun onItemClick() {
        mListener.onItemClick(article)
    }

    interface FavoritesItemViewModelListener :
        BaseItemListener<Article>

}