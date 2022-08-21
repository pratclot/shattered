package com.pratclot

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import br.com.concretesolutions.kappuccino.custom.recyclerView.RecyclerViewInteractions.recyclerView
import com.pratclot.news.R
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@LargeTest
internal class MyNewsActivityTest : BaseUITest() {

    @Test
    fun whenLoadingIsFinished_thenUserCanSelectNews() {
        myNews {
            recyclerView(R.id.newsRecyclerView) {
                sizeIs(30)
                atPosition(29) {
                    scroll()
                    click()
                }
            }
            uiDevice.pressBack()
        }
    }
}
