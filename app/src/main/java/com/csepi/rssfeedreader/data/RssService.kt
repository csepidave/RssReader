package com.csepi.rssfeedreader.data

import com.csepi.rssfeedreader.data.model.Rss
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface RssService {
    @GET
    fun getRssFeed(@Url url: String) : Single<Rss>
}