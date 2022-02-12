package com.app.featureui.launchlist.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable


@Composable
internal fun LaunchListScreen(viewModel : LaunchListViewModel) {

    LaunchListScreenContent()
}

@Composable
private fun LaunchListScreenContent() {
    Column {
        Text("Blah")
    }
}