package com.codehangouts.newsfeed.ui.main.article

import androidx.databinding.ObservableField
import com.codehangouts.newsfeed.ui.base.BaseItemListener

class ArticleItemViewModel(
    private val article: ArticleDataItem,
    private val mListener: ArticleItemViewModelListener
) {
    val imageUrl: ObservableField<String?> = ObservableField(article.imageUrl)
    val title: ObservableField<String?> = ObservableField(article.title)
    val byline: ObservableField<String?> = ObservableField(article.byline)
    val publishedDate: ObservableField<String?> = ObservableField(article.publishedDate)

    fun onItemClick() {
        mListener.onItemClick(article)
    }

    interface ArticleItemViewModelListener :
        BaseItemListener<ArticleDataItem>

}