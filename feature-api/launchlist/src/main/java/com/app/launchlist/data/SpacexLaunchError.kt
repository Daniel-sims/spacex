package com.app.launchlist.data

sealed class SpacexLaunchError {
    object BadRequest : SpacexLaunchError()
}