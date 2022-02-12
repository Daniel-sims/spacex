package com.app.core.di.modules

import com.app.core.dispatcher.DispatchProvider
import com.app.core.dispatcher.DispatchProviderImpl
import org.koin.dsl.module

internal val dataModule = module {
    single<DispatchProvider> { DispatchProviderImpl() }
}