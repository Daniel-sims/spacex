package com.app.launchlist.di.modules

import com.app.launchlist.mappers.SpacexLaunchMapper
import com.app.launchlist.repositories.SpacexLaunchRepository
import com.app.launchlist.repositories.SpacexLaunchRepositoryImpl
import org.koin.dsl.module

internal val dataModule = module {
    single<SpacexLaunchRepository> {
        SpacexLaunchRepositoryImpl(
            spacexServices = get(),
            spacexLaunchMapper = get(),
            dispatchProvider = get()
        )
    }

    single {
        SpacexLaunchMapper()
    }
}