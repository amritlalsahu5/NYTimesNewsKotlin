package com.codehangouts.newsfeed.ui.main.article

import android.view.LayoutInflater
import android.view.ViewGroup
import com.codehangouts.newsfeed.databinding.ItemArticleEmptyViewBinding
import com.codehangouts.newsfeed.databinding.ItemArticleViewBinding
import com.codehangouts.newsfeed.ui.base.BaseEmptyItemListener
import com.codehangouts.newsfeed.ui.base.BaseItemListener
import com.codehangouts.newsfeed.ui.base.BaseRecyclerViewAdapter
import com.codehangouts.newsfeed.ui.base.BaseViewHolder
import com.codehangouts.newsfeed.ui.main.article.ArticleItemViewModel.ArticleItemViewModelListener
import com.codehangouts.newsfeed.utils.AppConstants.VIEW_TYPE_EMPTY
import com.codehangouts.newsfeed.utils.AppConstants.VIEW_TYPE_NORMAL

class ArticleAdapter(items: MutableList<ArticleDataItem>) :
    BaseRecyclerViewAdapter<ArticleDataItem>(items) {
    private lateinit var mListener: ArticleAdapterListener

    fun setListener(listener: ArticleAdapterListener) {
        mListener = listener
    }

    override fun getItemCount(): Int {
        return if (items.size > 0) items.size else 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (items.isNotEmpty()) VIEW_TYPE_NORMAL else VIEW_TYPE_EMPTY
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            VIEW_TYPE_NORMAL -> {
                ArticleViewHolder(
                    ItemArticleViewBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                    )
                )
            }
            else -> {
                EmptyViewHolder(
                    ItemArticleEmptyViewBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                    )
                )
            }
        }
    }

    interface ArticleAdapterListener : BaseItemListener<ArticleDataItem>,
        BaseEmptyItemListener

    inner class ArticleViewHolder(private val mBinding: ItemArticleViewBinding) :
        BaseViewHolder(mBinding.root), ArticleItemViewModelListener {
        override fun onBind(position: Int) {
            val article = items[position]
            mBinding.viewModel =
                ArticleItemViewModel(
                    article,
                    this
                )
            mBinding.executePendingBindings()
        }

        override fun onItemClick(item: ArticleDataItem) {
            mListener.onItemClick(item)
        }

    }

    inner class EmptyViewHolder(private val mBinding: ItemArticleEmptyViewBinding) :
        BaseViewHolder(mBinding.root),
        BaseEmptyItemListener {
        override fun onBind(position: Int) {
            mBinding.viewModel =
                ArticleEmptyItemViewModel(
                    this
                )
            mBinding.executePendingBindings()
        }

        override fun onRetryClick() {
            mListener.onRetryClick()
        }

    }

}