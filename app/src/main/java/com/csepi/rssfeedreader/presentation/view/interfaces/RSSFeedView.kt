package com.csepi.rssfeedreader.presentation.view.interfaces

import com.csepi.rssfeedreader.data.model.Article

interface RSSFeedView {
    fun showArticles(articles: List<Article>?)
    fun showError(error: Throwable)
    fun startLoading()
    fun stopLoading()
}