package com.app.launchlist.repositories

import com.app.core.Resource
import com.app.core.dispatcher.DispatchProvider
import com.app.launchlist.data.SpacexLaunch
import com.app.launchlist.data.SpacexLaunchError
import com.app.launchlist.mappers.SpacexLaunchMapper
import com.app.launchlist.services.SpacexServices
import com.app.networking.utils.safeApiCall
import kotlinx.coroutines.withContext

internal class SpacexLaunchRepositoryImpl(
    private val spacexServices: SpacexServices,
    private val spacexLaunchMapper: SpacexLaunchMapper,
    private val dispatchProvider: DispatchProvider
) : SpacexLaunchRepository {

    override suspend fun getLatestLaunchList(): Resource<List<SpacexLaunch>, SpacexLaunchError> =
        withContext(dispatchProvider.IO) {
            val remoteListResponse = safeApiCall { spacexServices.getLaunches() }
            val remoteData = remoteListResponse.body()
            if (remoteListResponse.isSuccessful && remoteData != null) {
                Resource.Success(spacexLaunchMapper.toListOrNull(remoteData))
            } else {
                Resource.Error(spacexLaunchMapper.toError(remoteListResponse))
            }
        }
}