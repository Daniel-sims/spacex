package com.app.featureui.launchlist.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.launchlist.data.SpacexLaunch
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
            val launchItems = response.result
            if (response.isSuccessful && launchItems != null) {
                uiState = uiState.copy(
                    launchItems = launchItems,
                    isLoading = false
                )
            } else {

            }
        }
    }


}