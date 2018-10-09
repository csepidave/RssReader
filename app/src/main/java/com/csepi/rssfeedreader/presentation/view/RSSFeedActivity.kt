package com.csepi.rssfeedreader.presentation.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View.GONE
import android.view.View.VISIBLE
import com.csepi.rssfeedreader.R
import com.csepi.rssfeedreader.data.model.Article
import com.csepi.rssfeedreader.presentation.presenter.RSSFeedPresenter
import com.csepi.rssfeedreader.presentation.view.adapters.FeedAdapter
import com.csepi.rssfeedreader.presentation.view.interfaces.RSSFeedView
import kotlinx.android.synthetic.main.activity_rssfeed.*

class RSSFeedActivity : AppCompatActivity(), RSSFeedView {

    private val rssFeedPresenter by lazy { RSSFeedPresenter(this) }
    private val feedAdapter by lazy { FeedAdapter() }
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rssfeed)

        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        feedAdapter.shareClickListener = (object: FeedAdapter.OnShareClickListener {
            override fun onShareClick(position: Int, item: Article) {
                rssFeedPresenter.shareArticleWithLink(item.link, item.articleTitle,
                        applicationContext)
            }
        })
        recyclerView.adapter = feedAdapter
        swipeRefreshLayout.isEnabled = false
        loadData(false)

        swipeRefreshLayout.setOnRefreshListener {
            loadData(true)
        }
    }

    private fun loadData(pullToRefresh: Boolean) {
        rssFeedPresenter.getRssContent("24ora/rss?rovatkeres=osszes", pullToRefresh)
    }

    override fun showArticles(articles: List<Article>) {
        recyclerView.visibility = VISIBLE
        feedAdapter.setData(articles)
    }

    override fun hideError() {
        errorView.visibility = GONE
    }

    override fun enablePullToRefresh(enable: Boolean) {
        swipeRefreshLayout.isEnabled = enable
    }

    override fun hideRefreshIndicator() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun showError(error: Throwable) {
        errorView.visibility = VISIBLE
        errorView.text = error.message
        feedAdapter.setData(mutableListOf())
        recyclerView.visibility = GONE
    }

    override fun startLoading() {
        progressBar.visibility = VISIBLE
    }

    override fun stopLoading() {
        progressBar.visibility = GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        rssFeedPresenter.clear()
    }
}
