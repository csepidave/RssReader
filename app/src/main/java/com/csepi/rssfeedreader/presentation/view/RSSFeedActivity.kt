package com.csepi.rssfeedreader.presentation.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.csepi.rssfeedreader.R
import com.csepi.rssfeedreader.data.model.Article
import com.csepi.rssfeedreader.presentation.presenter.RSSFeedPresenter
import com.csepi.rssfeedreader.presentation.view.interfaces.RSSFeedView

class RSSFeedActivity : AppCompatActivity(), RSSFeedView {

    private val rssFeedPresenter by lazy { RSSFeedPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rssfeed)
        rssFeedPresenter.getRssContent("24ora/rss?rovatkeres=osszes")
    }

    override fun showArticles(articles: List<Article>?) {
        articles?.let {
            Toast.makeText(this, it.size.toString(), Toast.LENGTH_LONG).show()
        }
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
