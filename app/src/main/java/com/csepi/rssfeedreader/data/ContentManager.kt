package com.csepi.rssfeedreader.data

import com.csepi.rssfeedreader.data.model.Rss
import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject

class ContentManager @Inject constructor(retrofit: Retrofit) {
    private val service = retrofit.create<RssService>(RssService::class.java)

    fun requestRssFeed(url: String) : Single<Rss> {
        return service.getRssFeed(url)
    }
}