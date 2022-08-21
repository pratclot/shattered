package com.pratclot

import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import dagger.hilt.android.testing.HiltAndroidRule
import org.junit.Before
import org.junit.Rule

abstract class BaseUITest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MyNewsActivity::class.java)

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    lateinit var uiDevice: UiDevice

    @Before
    fun setUp() {
        activityRule.scenario.onActivity {
            IdlingRegistry.getInstance().register(it.idlingResource)
            uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        }
    }
}
