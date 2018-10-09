package com.csepi.rssfeedreader.data.model

import com.csepi.rssfeedreader.utils.empty
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root(name = "enclosure", strict = false)
data class Enclosure(
        @field:Attribute(name = "url", required = false)
        var url: String = String.empty(),

        @field:Attribute(name = "width", required = false)
        var width: String = String.empty(),

        @field:Attribute(name = "height", required = false)
        var height: String = String.empty(),

        @field:Attribute(name = "type", required = false)
        var contentType: String = String.empty()
)