package com.app.launchlist.mappers

import com.app.launchlist.data.SpacexLaunch
import com.app.launchlist.data.SpacexLaunchError
import com.app.launchlist.responses.SpacexLaunchResponse
import org.joda.time.DateTime
import retrofit2.Response
import java.net.HttpURLConnection
import java.text.SimpleDateFormat
import java.util.*

internal class SpacexLaunchMapper {

    fun toListOrNull(
        spacexLaunchResponseList: List<SpacexLaunchResponse>?
    ): List<SpacexLaunch>? = spacexLaunchResponseList?.mapNotNull {
        try {

            // Any missing information would be handled in here.
            // As per requires, with suitable other options provided.
            // I just couldn't find any problems with their data.
            // A couple of examples of why the mapper is used provided.
            SpacexLaunch(
                launchName = it.name,
                launchDate = DateTime.parse(it.launchDateUtc),
                launchDateFriendlyName = SimpleDateFormat(
                    "d MMMM, yyyy",
                    Locale.getDefault()
                ).format(DateTime.parse(it.launchDateUtc).toDate()),
                successful = it.success,
                imageUrl = it.links?.patch?.small ?: "https://picsum.photos/200"
            )
        } catch (ex: Exception) {
            // Log to some logger such as Sentry to monitor exceptions during mapping
            // as it may flag a problem with malformed data coming from the backend. Unit
            // tests should try and break the data passed in as much as possible so you
            // can communicate with the BE team potential issues.
            null
        }
    }

    fun toError(error: Response<*>): SpacexLaunchError = when (error.code()) {
        // Handle errors here using sealed classes to pass data back if required etc.
        HttpURLConnection.HTTP_BAD_REQUEST -> SpacexLaunchError.BadRequest
        else -> SpacexLaunchError.BadRequest
    }
}