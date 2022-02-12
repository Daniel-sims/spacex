package com.app.launchlist.responses

import com.google.gson.annotations.SerializedName

data class SpacexLaunchResponse(
    @SerializedName("name")
    val name : String,

    @SerializedName("success")
    val success : Boolean,

    @SerializedName("date_utc")
    val launchDateUtc : String
)