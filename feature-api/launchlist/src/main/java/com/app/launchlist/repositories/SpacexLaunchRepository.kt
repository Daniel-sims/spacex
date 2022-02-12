package com.app.launchlist.repositories

import com.app.core.Resource
import com.app.launchlist.data.SpacexLaunch
import com.app.launchlist.data.SpacexLaunchError

interface SpacexLaunchRepository {

    suspend fun getLatestLaunchList(): Resource<List<SpacexLaunch>, SpacexLaunchError>
}