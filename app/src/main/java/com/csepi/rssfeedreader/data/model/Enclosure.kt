package com.csepi.rssfeedreader.data.model

import com.csepi.rssfeedreader.utils.empty
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "enclosure", strict = false)
data class Enclosure(@field:Element(name = "url", required = false) var url: String = String.empty(),
                     @field:Element(name = "width", required = false) var width: String = String.empty(),
                     @field:Element(name = "height", required = false) var height: String = String.empty(),
                     @field:Element(name = "type", required = false) var contentType: String = String.empty())