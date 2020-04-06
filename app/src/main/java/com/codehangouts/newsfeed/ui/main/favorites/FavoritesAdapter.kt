package com.codehangouts.newsfeed.ui.main.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import com.codehangouts.newsfeed.data.model.db.Article
import com.codehangouts.newsfeed.databinding.ItemFavoritesEmptyViewBinding
import com.codehangouts.newsfeed.databinding.ItemFavoritesEmptyViewBindingImpl
import com.codehangouts.newsfeed.databinding.ItemFavoritesViewBinding
import com.codehangouts.newsfeed.ui.base.BaseItemListener
import com.codehangouts.newsfeed.ui.base.BaseRecyclerViewAdapter
import com.codehangouts.newsfeed.ui.base.BaseViewHolder
import com.codehangouts.newsfeed.ui.main.favorites.FavoritesItemViewModel.FavoritesItemViewModelListener
import com.codehangouts.newsfeed.utils.AppConstants.VIEW_TYPE_EMPTY
import com.codehangouts.newsfeed.utils.AppConstants.VIEW_TYPE_NORMAL

class FavoritesAdapter(items: MutableList<Article>) :
    BaseRecyclerViewAdapter<Article>(items) {
    private lateinit var mListener: FavoritesAdapterListener

    fun setListener(listener: FavoritesAdapterListener) {
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
                FavoritesViewHolder(
                    ItemFavoritesViewBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                    )
                )
            }
            else -> {
                EmptyViewHolder(
                    ItemFavoritesEmptyViewBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                    )
                )
            }
        }
    }

    interface FavoritesAdapterListener :
        BaseItemListener<Article>

    inner class FavoritesViewHolder(private val mBinding: ItemFavoritesViewBinding) :
        BaseViewHolder(mBinding.root), FavoritesItemViewModelListener {
        override fun onBind(position: Int) {
            val article = items[position]
            mBinding.viewModel =
                FavoritesItemViewModel(
                    article,
                    this
                )
            mBinding.executePendingBindings()
        }

        override fun onItemClick(item: Article) {
            mListener.onItemClick(item)
        }

    }

    inner class EmptyViewHolder(private val mBinding: ItemFavoritesEmptyViewBinding) :
        BaseViewHolder(mBinding.root) {
        override fun onBind(position: Int) {
            mBinding.viewModel =
                FavoritesEmptyItemViewModel()
            mBinding.executePendingBindings()
        }
    }

}