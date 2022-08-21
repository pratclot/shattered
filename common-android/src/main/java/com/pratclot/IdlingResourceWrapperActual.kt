package com.pratclot

import androidx.test.espresso.IdlingResource
import javax.inject.Inject

class IdlingResourceWrapperActual @Inject constructor() : IdlingResourceWrapper {
    override fun setBusy() {
    }

    override fun setFree() {
    }

    override val idlingResource: IdlingResource
        get() = object : IdlingResource {
            override fun getName(): String {
                return "dummy"
            }

            override fun isIdleNow(): Boolean {
                return true
            }

            override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
            }
        }
}
