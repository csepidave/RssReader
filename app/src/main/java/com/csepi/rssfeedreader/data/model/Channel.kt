package com.csepi.rssfeedreader.data.model

import com.csepi.rssfeedreader.data.model.Article
import com.csepi.rssfeedreader.utils.empty
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "channel", strict = false)
data class Channel(@field:Element(name = "title") val title: String = String.empty(),
                   @field:Element(name = "language") val language: String = String.empty(),
                   @field:ElementList(entry = "item", inline = true) val articles: List<Article> = emptyList()
)
