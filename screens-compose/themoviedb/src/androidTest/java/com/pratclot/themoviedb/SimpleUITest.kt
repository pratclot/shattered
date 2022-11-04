package com.pratclot.themoviedb

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pratclot.themoviedb.composables.Greeting
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
// @LargeTest
internal class SimpleUITest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MoviesActivity>()
//    @get:Rule
//    val composeTestRule = createComposeRule()

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun canBrowseTheLoadedList() {
        composeTestRule.setContent {
//            GenericAndroidProjectTheme {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
            Greeting(navigate = { })
//                }
//            }
        }
//        composeTestRule.onNodeWithText("Black Adam").performClick()
    }
}
