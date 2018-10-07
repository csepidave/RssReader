package com.csepi.rssfeedreader.presentation.presenter

import com.csepi.rssfeedreader.data.ContentManager
import com.csepi.rssfeedreader.di.DaggerAppComponent
import com.csepi.rssfeedreader.presentation.view.interfaces.RSSFeedView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RSSFeedPresenter(private val view: RSSFeedView?) {

    init {
        DaggerAppComponent.create().inject(this)
    }

    @Inject
    lateinit var contentManager: ContentManager

    private val disposables = CompositeDisposable()

    fun getRssContent(url: String) {
        val disposable = contentManager.requestRssFeed(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ feed ->
                    feed?.let {
                        view?.showArticles(it.channel?.articles)
                    }
                }, {
                    view?.showError(it)
                })
        disposables.add(disposable)
    }

    fun clear() {
        disposables.clear()
    }
}