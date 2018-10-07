package com.csepi.rssfeedreader.data.model

import com.csepi.rssfeedreader.utils.empty
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
data class Article(@field:Element(name = "title") val articleTitle: String = String.empty(),
                   @field:Element(name = "link") val link: String = String.empty(),
                   @field:Element(name = "pubDate") val publishDate: String = String.empty(),
                   @field:Element(name = "description") val description: String = String.empty(),
                   @field:Element(name = "category") val category: String = String.empty(),
                   @field:ElementList(entry = "title", inline = true) val enclosures: List<Enclosure> = emptyList()
                   )