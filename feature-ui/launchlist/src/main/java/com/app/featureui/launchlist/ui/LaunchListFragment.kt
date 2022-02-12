package com.app.featureui.launchlist.ui

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.app.styles.SpacexTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class LaunchListFragment : Fragment() {

    private val viewModel by viewModel<LaunchListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            SpacexTheme {
                LaunchListScreen(viewModel)
            }
        }
    }

}