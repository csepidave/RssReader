package com.csepi.rssfeedreader.data

import com.csepi.rssfeedreader.data.model.RssFeed
import io.reactivex.Single
import retrofit2.Retrofit

class ContentManager constructor(retrofit: Retrofit) {
    private val service = retrofit.create<RssService>(RssService::class.java)

    fun requestRssFeed(url: String) : Single<RssFeed> {
        return service.getRssFeed(url)
    }
}