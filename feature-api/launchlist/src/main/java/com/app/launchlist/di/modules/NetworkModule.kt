package com.app.launchlist.di.modules

import com.app.launchlist.services.SpacexServices
import com.app.networking.utils.createService
import org.koin.dsl.module

internal val networkModule = module {
    single {
        createService(
            networking = get(),
            clazz = SpacexServices::class.java
        )
    }
}