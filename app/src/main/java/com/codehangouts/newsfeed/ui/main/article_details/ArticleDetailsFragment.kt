package com.codehangouts.newsfeed.ui.main.article_details

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.codehangouts.newsfeed.BR
import com.codehangouts.newsfeed.R
import com.codehangouts.newsfeed.ViewModelProviderFactory
import com.codehangouts.newsfeed.databinding.FragmentArticleDetailsBinding
import com.codehangouts.newsfeed.ui.base.BaseFragment
import com.codehangouts.newsfeed.ui.main.MainActivity
import com.codehangouts.newsfeed.ui.main.article.ArticleDataItem
import com.codehangouts.newsfeed.utils.AppConstants
import javax.inject.Inject

class ArticleDetailsFragment :
    BaseFragment<FragmentArticleDetailsBinding, ArticleDetailsViewModel>(),
    ArticleDetailsNavigator {
    @Inject
    lateinit var factory: ViewModelProviderFactory
    private var articleDetailsViewModel: ArticleDetailsViewModel? = null
    private var articleDataItem: ArticleDataItem? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_article_details

    override val viewModel: ArticleDetailsViewModel
        get() {
            articleDetailsViewModel =
                ViewModelProvider(this, factory).get(ArticleDetailsViewModel::class.java)
            return articleDetailsViewModel as ArticleDetailsViewModel
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        articleDetailsViewModel?.setNavigator(this)
        if (arguments != null) {
            articleDataItem = arguments?.getParcelable(AppConstants.ARTICLE)
            if (articleDataItem != null) { // To check if article is favorite or not
                articleDataItem?.id?.let { articleDetailsViewModel?.findById(it) }
            }
        }
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        setUpToolbar()
        setArticle()
    }

    private fun setArticle() {
        if (articleDataItem != null) {
            getViewDataBinding().article = articleDataItem
        }
    }

    private fun setUpToolbar() {
        if (activity != null) {
            (activity as MainActivity).setSupportActionBar(getViewDataBinding().toolbar)
            val actionBar = (activity as MainActivity).supportActionBar
            actionBar?.setDisplayHomeAsUpEnabled(true)
            actionBar?.setDisplayShowHomeEnabled(true)
            actionBar?.setDisplayShowTitleEnabled(false)
        }
        getViewDataBinding().toolbar.setNavigationOnClickListener {
            if (activity != null) {
                activity?.onBackPressed()
            }
        }
    }
}