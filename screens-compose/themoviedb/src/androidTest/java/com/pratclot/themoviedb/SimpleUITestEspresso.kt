package com.pratclot.themoviedb

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import br.com.concretesolutions.kappuccino.assertions.VisibilityAssertions.displayed
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@LargeTest
internal class SimpleUITestEspresso : BaseUITest() {

    @Test
    fun canBrowseTheLoadedList() {
        displayed {
            text("Black Adam")
        }
    }
}
