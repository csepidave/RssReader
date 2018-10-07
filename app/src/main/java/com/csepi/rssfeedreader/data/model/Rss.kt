package com.csepi.rssfeedreader.data.model

import com.csepi.rssfeedreader.data.model.Channel
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
data class Rss(@field:Element(name = "channel") val channel: Channel? = null)