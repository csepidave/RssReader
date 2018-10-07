package com.csepi.rssfeedreader.di

import com.csepi.rssfeedreader.data.ContentManager
import com.csepi.rssfeedreader.presentation.presenter.RSSFeedPresenter
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    fun getContentManager() : ContentManager
    fun inject(rssFeedPresenter: RSSFeedPresenter)
}