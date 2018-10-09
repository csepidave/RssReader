package com.csepi.rssfeedreader.presentation.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
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
                Toast.makeText(applicationContext, "Share", Toast.LENGTH_LONG).show()
            }
        })
        recyclerView.adapter = feedAdapter
        rssFeedPresenter.getRssContent("24ora/rss?rovatkeres=osszes")
    }

    override fun showArticles(articles: List<Article>) {
            feedAdapter.setData(articles)
    }

    override fun showError(error: Throwable) {
        Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
    }

    override fun startLoading() {

    }

    override fun stopLoading() {

    }

    override fun onDestroy() {
        super.onDestroy()
        rssFeedPresenter.clear()
    }
}
