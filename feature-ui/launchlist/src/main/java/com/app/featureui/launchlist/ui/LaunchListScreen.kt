package com.app.featureui.launchlist.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.app.launchlist.data.SpacexLaunch


@Composable
internal fun LaunchListScreen(viewModel: LaunchListViewModel) {

    LaunchListScreenContent(
        uiState = viewModel.uiState
    )
}

@Composable
private fun LaunchListScreenContent(
    uiState: LaunchListViewModel.LaunchListUiState
) {
    if (uiState.launchItems.isNotEmpty()) {
        LaunchListItems(items= uiState.launchItems)
    }
}

@Composable
private fun LaunchListItems(
    modifier: Modifier = Modifier,
    items: List<SpacexLaunch>
) {
    LazyColumn(modifier = modifier) {

        items.forEach { spacexLaunch ->
            item {
                Text(spacexLaunch.launchName)
            }
        }
    }
}