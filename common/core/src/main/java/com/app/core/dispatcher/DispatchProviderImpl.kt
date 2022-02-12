package com.app.core.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal class DispatchProviderImpl : DispatchProvider {

    override val Main: CoroutineDispatcher
        get() = Dispatchers.Main

    override val IO: CoroutineDispatcher
        get() = Dispatchers.IO

    override val Default: CoroutineDispatcher
        get() = Dispatchers.Default
}