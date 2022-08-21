package com.pratclot

import androidx.test.espresso.IdlingResource

interface IdlingResourceProvider {
    val idlingResource: IdlingResource
}
