package com.app.core.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class TestDispatchProviderImpl : DispatchProvider {

    override val Main: CoroutineDispatcher
        get() = Dispatchers.Unconfined

    override val IO: CoroutineDispatcher
        get() = Dispatchers.Unconfined

    override val Default: CoroutineDispatcher
        get() = Dispatchers.Unconfined
}