package com.app.featureui.launchlist.di.modules

import com.app.featureui.launchlist.ui.LaunchListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {
    viewModel {
        LaunchListViewModel()
    }
}