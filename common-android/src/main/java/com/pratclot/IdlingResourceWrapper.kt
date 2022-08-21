package com.pratclot

import androidx.test.espresso.IdlingResource

interface IdlingResourceWrapper : IdlingResourceProvider {
    override val idlingResource: IdlingResource
    fun setBusy()
    fun setFree()
}
