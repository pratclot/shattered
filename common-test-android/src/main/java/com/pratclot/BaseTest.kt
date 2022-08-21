@file:OptIn(ExperimentalCoroutinesApi::class)

package com.pratclot

import android.net.Uri
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.every
import io.mockk.mockkStatic
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import org.junit.Rule

abstract class BaseTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule(StandardTestDispatcher())

    val dispatcherWrapper: DispatcherWrapper get() = coroutinesTestRule.testDispatcherWrapper

    init {
//        took from https://stackoverflow.com/a/55251961/13442292
        mockkStatic(Log::class)
        every { Log.v(any(), any()) } returns 0
        every { Log.d(any(), any()) } returns 0
        every { Log.i(any(), any()) } returns 0
        every { Log.e(any(), any()) } returns 0

        mockkStatic(Uri::class)
    }
}
