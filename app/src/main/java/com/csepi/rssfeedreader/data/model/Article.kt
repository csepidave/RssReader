package com.csepi.rssfeedreader.data.model

import com.csepi.rssfeedreader.utils.empty
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Path
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
data class Article(@field:Element(name = "title") var articleTitle: String = String.empty(),
                   @field:Element(name = "link") var link: String = String.empty(),
                   @field:Element(name = "pubDate") var publishDate: String = String.empty(),
                   @field:Element(name = "description", required = false) var description: String = String.empty(),
                   @field:Path("category") var category: String = String.empty(),
                   @field:ElementList(entry = "enclosure", inline = true, required = false) var enclosures: MutableList<Enclosure> = mutableListOf()
                   )