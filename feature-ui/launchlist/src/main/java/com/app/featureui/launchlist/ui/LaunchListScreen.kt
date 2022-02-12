package com.app.featureui.launchlist.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.app.featureui.launchlist.R
import com.app.launchlist.data.SpacexLaunch


@ExperimentalCoilApi
@Composable
internal fun LaunchListScreen(viewModel: LaunchListViewModel) {

    LaunchListScreenContent(
        uiState = viewModel.uiState
    )
}

@ExperimentalCoilApi
@Composable
private fun LaunchListScreenContent(
    uiState: LaunchListViewModel.LaunchListUiState
) {

    when {
        uiState.isLoading -> {
            LoadingContent(modifier = Modifier.fillMaxSize())
        }
        uiState.launchItems.isNotEmpty() -> {
            ListContent(modifier = Modifier.fillMaxSize(), items = uiState.launchItems)
        }
        uiState.error != null -> {
            // Not a requirement to style this but you can see how error gets here
            Text(text = uiState.error)
        }
    }
}

@Composable
private fun LoadingContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.launch_list_loading_message),
            style = MaterialTheme.typography.h5
        )
        Spacer(modifier = Modifier.padding(vertical = dimensionResource(com.app.styles.R.dimen.spacing_1x)))
        CircularProgressIndicator(
            color = MaterialTheme.colors.onSurface
        )
    }
}

@ExperimentalCoilApi
@Composable
private fun ListContent(
    modifier: Modifier = Modifier,
    items: List<SpacexLaunch>
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(all = dimensionResource(com.app.styles.R.dimen.spacing_2x)),
            text = stringResource(R.string.launch_list_title),
            style = MaterialTheme.typography.h5
        )
        if (items.isEmpty()) {
            TODO("Handle empty list (Not in reqs")
        } else {
            LazyColumn {
                items.forEach { spacexLaunch ->
                    item {
                        LaunchItem(
                            modifier = Modifier.fillMaxWidth(),
                            spacexLaunch = spacexLaunch
                        ) {
                            // Handle click event in here.
                            // Ideally this would call the viewmodel to handle the click
                            // event through a navigation handler defined in the :app module
                            // to navigate where it needs to, or hoist into your fragment
                            // to handle navigation the old way (untestable).
                        }
                        Divider(
                            modifier = Modifier.fillMaxWidth(),
                            color = Color.Black.copy(alpha = 0.20f)
                        )
                    }
                }
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
private fun LaunchItem(
    modifier: Modifier = Modifier,
    spacexLaunch: SpacexLaunch,
    onItemClick: () -> Unit
) {

    Row(
        modifier = modifier.clickable {
            onItemClick()
        }.padding(
            horizontal = dimensionResource(com.app.styles.R.dimen.spacing_2x),
            vertical = dimensionResource(com.app.styles.R.dimen.spacing_1x)
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Surface(
            shape = RoundedCornerShape(size = dimensionResource(com.app.styles.R.dimen.spacing_2x))
        ) {
            Box(
                modifier = Modifier.size(size = dimensionResource(R.dimen.spacex_launch_image_size)),
                contentAlignment = Alignment.Center
            ) {
                val painterState = rememberImagePainter(data = spacexLaunch.imageUrl)

                Image(
                    painter = painterState,
                    contentDescription = null,
                )

                if (painterState.state !is ImagePainter.State.Success) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colors.onSurface
                    )
                }
            }
        }

        Column(
            modifier = Modifier.padding(start = dimensionResource(com.app.styles.R.dimen.spacing_1x)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(com.app.styles.R.dimen.spacing_1x))
        ) {
            Text(text = spacexLaunch.launchName, style = MaterialTheme.typography.body1)

            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = spacexLaunch.launchDateFriendlyName,
                    style = MaterialTheme.typography.body2
                )
            }
            Icon(
                imageVector = if (spacexLaunch.successful) {
                    Icons.Default.Check
                } else {
                    Icons.Default.Close
                },
                tint = if (spacexLaunch.successful) {
                    Color.Green
                } else {
                    Color.Red
                },
                contentDescription = ""
            )
        }
    }
}