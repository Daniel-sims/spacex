package com.app.featureui.launchlist.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.launchlist.data.SpacexLaunch
import com.app.launchlist.data.SpacexLaunchError
import com.app.launchlist.repositories.SpacexLaunchRepository
import kotlinx.coroutines.launch

internal class LaunchListViewModel(
    private val spacexLaunchRepository: SpacexLaunchRepository
) : ViewModel() {

    var uiState by mutableStateOf(LaunchListUiState())
        private set

    data class LaunchListUiState(
        val isLoading: Boolean = true,
        val error: String? = null,
        val launchItems: List<SpacexLaunch> = listOf()
    )

    init {
        fetchLatestLaunchList()
    }

    private fun fetchLatestLaunchList() {
        viewModelScope.launch {
            val response = spacexLaunchRepository.getLatestLaunchList()

            // API doesn't offer sorting. Ideally this would be done on the BE already, so avoid a performance hit.
            val launchItems = response.result?.reversed()
            uiState = if (response.isSuccessful && launchItems != null) {
                uiState.copy(
                    launchItems = launchItems,
                    isLoading = false
                )
            } else {
                uiState.copy(
                    error = when (response.error) {

                        // These would be padded out to handle more error messages.
                        // Also strings would be provided by a string provider so these
                        // can be localised without the requirement for context in the viewmodel.
                        is SpacexLaunchError.BadRequest -> "Bad Request"
                        else -> "Something unexpected went wrong."
                    },
                    isLoading = false
                )
            }
        }
    }


}