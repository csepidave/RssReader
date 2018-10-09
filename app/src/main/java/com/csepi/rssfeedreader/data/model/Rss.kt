package com.csepi.rssfeedreader.data.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
data class Rss(
        @field:Element(name = "channel")
        var channel: Channel? = null
)