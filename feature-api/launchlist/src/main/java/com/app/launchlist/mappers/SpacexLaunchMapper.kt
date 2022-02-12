package com.app.launchlist.mappers

import com.app.launchlist.data.SpacexLaunch
import com.app.launchlist.data.SpacexLaunchError
import com.app.launchlist.responses.SpacexLaunchResponse
import org.joda.time.DateTime
import retrofit2.Response

internal class SpacexLaunchMapper {

    fun toListOrNull(
        spacexLaunchResponseList: List<SpacexLaunchResponse>?
    ): List<SpacexLaunch>? = spacexLaunchResponseList?.mapNotNull {
        try {
            SpacexLaunch(
                launchName = it.name,
                launchDate = DateTime.parse(it.launchDateUtc),
                successful = it.success
            )
        } catch (ex : Exception) {
            // Log to some logger such as Sentry to monitor exceptions during mapping
            // as it may flag a problem with malformed data coming from the backend. Unit
            // tests should try and break the data passed in as much as possible so you
            // can communicate with the BE team potential issues.
            null
        }
    }

    fun toError(error : Response<*>) : SpacexLaunchError {
        return SpacexLaunchError.BadRequest
    }
}