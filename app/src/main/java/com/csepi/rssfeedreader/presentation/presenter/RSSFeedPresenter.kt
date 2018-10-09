package com.csepi.rssfeedreader.presentation.presenter

import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_SEND
import com.csepi.rssfeedreader.data.ContentManager
import com.csepi.rssfeedreader.di.DaggerAppComponent
import com.csepi.rssfeedreader.presentation.view.interfaces.RSSFeedView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import com.csepi.rssfeedreader.R


class RSSFeedPresenter(private val view: RSSFeedView?) {

    init {
        DaggerAppComponent.create().inject(this)
    }

    @Inject
    lateinit var contentManager: ContentManager

    private val disposables = CompositeDisposable()

    private val textIntentType = "text/plain"

    fun getRssContent(url: String, pullToRefresh: Boolean) {
        view?.enablePullToRefresh(pullToRefresh)
        if (!pullToRefresh) {
            view?.startLoading()
        }
        val disposable = contentManager.requestRssFeed(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate {
                    if (pullToRefresh) {
                        view?.hideRefreshIndicator()
                    } else {
                        view?.stopLoading()
                        view?.enablePullToRefresh(true)
                    }
                }
                .subscribe({ feed ->
                    feed?.let {
                        view?.hideError()
                        view?.showArticles(it.channel?.articles!!)
                    }
                }, {
                    view?.showError(it)
                })
        disposables.add(disposable)
    }

    fun shareArticleWithLink(url: String, title: String, context: Context) {
        val textToShare = "$title\n$url"
        val intent = Intent(ACTION_SEND)
        intent.apply {
            type = textIntentType
            putExtra(Intent.EXTRA_SUBJECT, title)
            putExtra(Intent.EXTRA_TEXT, textToShare)
        }
        context.startActivity(Intent.createChooser(intent,
                context.resources.getString(R.string.share)))
    }

    fun clear() {
        disposables.clear()
    }
}