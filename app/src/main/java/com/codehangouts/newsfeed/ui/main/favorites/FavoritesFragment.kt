package com.codehangouts.newsfeed.ui.main.favorites

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.codehangouts.newsfeed.BR
import com.codehangouts.newsfeed.R
import com.codehangouts.newsfeed.ViewModelProviderFactory
import com.codehangouts.newsfeed.data.model.db.Article
import com.codehangouts.newsfeed.databinding.FragmentFavoritesBinding
import com.codehangouts.newsfeed.ui.base.BaseFragment
import com.codehangouts.newsfeed.ui.main.MainActivity
import com.codehangouts.newsfeed.ui.main.article.ArticleDataItem
import com.codehangouts.newsfeed.ui.main.favorites.FavoritesAdapter.FavoritesAdapterListener
import com.codehangouts.newsfeed.utils.AppConstants
import javax.inject.Inject

class FavoritesFragment :
    BaseFragment<FragmentFavoritesBinding, FavoritesViewModel>(),
    FavoritesNavigator,
    FavoritesAdapterListener {
    @Inject
    lateinit var factory: ViewModelProviderFactory
    @Inject
    lateinit var favoritesAdapter: FavoritesAdapter
    private var favoritesViewModel: FavoritesViewModel? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_favorites

    override val viewModel: FavoritesViewModel
        get() {
            favoritesViewModel = ViewModelProvider(this, factory).get(
                FavoritesViewModel::class.java
            )
            return favoritesViewModel as FavoritesViewModel
        }

    override fun onItemClick(item: Article) {
        val bundle = Bundle()
        bundle.putParcelable(
            AppConstants.ARTICLE,
            ArticleDataItem(
                item.id
                , item.imageUrl
                , item.title
                , item.byline
                , item.abstractX
                , item.publishedDate
                , item.url
                , item.coverImageUrl
            )
        )
        getNavController().navigate(
            R.id.action_favoritesFragment_to_articleDetailsFragment,
            bundle
        )
    }

    override fun handleError(message: String?) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun setData(data: List<Article>) {
        favoritesAdapter.addItems(data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoritesViewModel?.setNavigator(this)
        favoritesAdapter.setListener(this)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        if (activity != null) {
            (activity as MainActivity).setSupportActionBar(getViewDataBinding().toolbar)
            getViewDataBinding().toolbar.title = getString(R.string.favorites)
            val actionBar = (activity as MainActivity).supportActionBar
            actionBar?.setDisplayHomeAsUpEnabled(true)
            actionBar?.setDisplayShowHomeEnabled(true)
        }
        getViewDataBinding().toolbar.setNavigationOnClickListener {
            if (activity != null) {
                activity?.onBackPressed()
            }
        }
        setHasOptionsMenu(true)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        getViewDataBinding().favoritesRecyclerView.layoutManager = LinearLayoutManager(
            activity
        )
        getViewDataBinding().favoritesRecyclerView.itemAnimator = DefaultItemAnimator()
        getViewDataBinding().favoritesRecyclerView.adapter = favoritesAdapter
    }
}