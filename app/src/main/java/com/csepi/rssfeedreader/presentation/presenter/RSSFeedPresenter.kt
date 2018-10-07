package com.csepi.rssfeedreader.presentation.presenter

import android.arch.lifecycle.ViewModel
import com.csepi.rssfeedreader.data.ContentManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RSSFeedPresenter : ViewModel() {

    @Inject
    lateinit var contentManager: ContentManager

    private val disposables = CompositeDisposable()

    fun getRssContent(url: String) {
        val disposable = contentManager.requestRssFeed(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.rss?.let {

                    }
                }, {

                })
        disposables.add(disposable)
    }
}