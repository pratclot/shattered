package com.pratclot

import androidx.test.espresso.idling.CountingIdlingResource
import javax.inject.Inject

class IdlingResourceWrapperTest @Inject constructor() : IdlingResourceWrapper {

    override val idlingResource = CountingIdlingResource("resource1")

    @Synchronized
    override fun setBusy() {
        idlingResource.increment()
    }

    @Synchronized
    override fun setFree() {
        idlingResource.decrement()
    }
}
