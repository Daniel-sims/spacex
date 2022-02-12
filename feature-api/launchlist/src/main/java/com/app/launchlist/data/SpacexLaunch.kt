package com.app.launchlist.data

import org.joda.time.DateTime

data class SpacexLaunch(
    val launchName: String,
    val launchDate: DateTime,
    val launchDateFriendlyName: String,
    val successful: Boolean,
    val imageUrl: String
)