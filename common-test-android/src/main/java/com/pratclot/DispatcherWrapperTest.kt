package com.pratclot

import kotlinx.coroutines.CoroutineDispatcher

class DispatcherWrapperTest(private val dispatcher: CoroutineDispatcher) : DispatcherWrapper {
    override val main: CoroutineDispatcher
        get() = dispatcher
    override val io: CoroutineDispatcher
        get() = dispatcher
    override val default: CoroutineDispatcher
        get() = dispatcher
    override val unconfined: CoroutineDispatcher
        get() = dispatcher
}
