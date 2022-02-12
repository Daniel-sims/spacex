package com.app.core.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

interface DispatchProvider {
    val Main: CoroutineDispatcher
    val IO: CoroutineDispatcher
    val Default: CoroutineDispatcher
}