package com.csepi.rssfeedreader.data.model

import com.csepi.rssfeedreader.utils.empty
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "enclosure", strict = false)
data class Enclosure(@field:Element(name = "url") val url: String = String.empty(),
                     @field:Element(name = "width") val width: String = String.empty(),
                     @field:Element(name = "height") val height: String = String.empty(),
                     @field:Element(name = "type") val contentType: String = String.empty())