package com.csepi.rssfeedreader.data.model

import com.csepi.rssfeedreader.utils.empty
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "channel", strict = false)
data class Channel(
        @field:Element(name = "title")
        var title: String = String.empty(),

        @field:Element(name = "language")
        var language: String = String.empty(),

        @field:ElementList(name = "item", entry = "item", inline = true)
        var articles: MutableList<Article> = mutableListOf()
)
