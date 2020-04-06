package com.codehangouts.newsfeed.ui.main.article

import com.codehangouts.newsfeed.ui.base.BaseEmptyItemListener

class ArticleEmptyItemViewModel(private val mListener: BaseEmptyItemListener) {
    fun onRetryClick() {
        mListener.onRetryClick()
    }

}