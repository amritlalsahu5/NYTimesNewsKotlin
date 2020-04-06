package com.codehangouts.newsfeed.ui.main.article

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.codehangouts.newsfeed.BR
import com.codehangouts.newsfeed.R
import com.codehangouts.newsfeed.ViewModelProviderFactory
import com.codehangouts.newsfeed.databinding.FragmentArticleBinding
import com.codehangouts.newsfeed.ui.base.BaseFragment
import com.codehangouts.newsfeed.ui.main.MainActivity
import com.codehangouts.newsfeed.ui.main.article.ArticleAdapter.ArticleAdapterListener
import com.codehangouts.newsfeed.utils.AppConstants
import javax.inject.Inject

class ArticleFragment : BaseFragment<FragmentArticleBinding, ArticleViewModel>(),
    ArticleNavigator, ArticleAdapterListener {
    @Inject
    lateinit var factory: ViewModelProviderFactory
    @Inject
    lateinit var articleAdapter: ArticleAdapter
    private var articleViewModel: ArticleViewModel? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_article

    override val viewModel: ArticleViewModel
        get() {
            articleViewModel = ViewModelProvider(this, factory).get(ArticleViewModel::class.java)
            return articleViewModel as ArticleViewModel
        }

    override fun onRetryClick() {
        articleViewModel?.fetchArticles(7)
    }

    override fun onItemClick(item: ArticleDataItem) {
        val bundle = Bundle()
        bundle.putParcelable(
            AppConstants.ARTICLE,
            item
        )
        getNavController().navigate(R.id.action_articleFragment_to_articleDetailsFragment, bundle)
    }

    override fun handleError(message: String?) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun setData(data: List<ArticleDataItem>) {
        articleAdapter.addItems(data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        articleViewModel?.setNavigator(this)
        articleAdapter.setListener(this)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        if (activity != null) (activity as MainActivity).setSupportActionBar(
            getViewDataBinding().toolbar
        )
        setHasOptionsMenu(true)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        getViewDataBinding().resultsBeanRecyclerView.layoutManager = LinearLayoutManager(activity)
        getViewDataBinding().resultsBeanRecyclerView.itemAnimator = DefaultItemAnimator()
        getViewDataBinding().resultsBeanRecyclerView.adapter = articleAdapter
    }

    override fun onCreateOptionsMenu(
        menu: Menu,
        inflater: MenuInflater
    ) {
        inflater.inflate(R.menu.main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorites) {
            getNavController().navigate(R.id.action_articleFragment_to_favoritesFragment)
        }
        return super.onOptionsItemSelected(item)
    }
}