package com.app.launchlist.responses

import com.google.gson.annotations.SerializedName

internal data class SpacexLaunchResponse(
    @SerializedName("name")
    val name: String,

    @SerializedName("success")
    val success: Boolean,

    @SerializedName("date_utc")
    val launchDateUtc: String,

    @SerializedName("links")
    val links: Links? = null
)

internal data class Links(
    @SerializedName("patch")
    val patch: Patch? = null
)

internal data class Patch(
    @SerializedName("small")
    val small: String? = null
)