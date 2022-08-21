@file:OptIn(ExperimentalCoroutinesApi::class)

package com.pratclot

import app.cash.turbine.test
import coil.ImageLoader
import com.jraska.livedata.test
import com.pratclot.common.UriValidator
import com.pratclot.domain.NewsItem
import com.pratclot.usecase.NewsUseCaseActual
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junitparams.JUnitParamsRunner
import junitparams.Parameters
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(JUnitParamsRunner::class)
internal class MyNewsViewModelTest : BaseTest() {

    private val useCase: NewsUseCaseActual = mockk()
    private val assetProvider: AssetProviderActual = mockk()

    /**
     * Pay attention, this is NOT a mock - coooooool, right?
     */
    private val uriValidator: UriValidator = UriValidator()
    private val idlingResourceWrapper: IdlingResourceWrapper = IdlingResourceWrapperActual()

    private val imageLoader: ImageLoader = mockk()

    private lateinit var viewModel: MyNewsViewModel

    @Before
    fun setup() {
        viewModel = MyNewsViewModel(
            useCase,
            uriValidator,
            idlingResourceWrapper,
            imageLoader,
        )
    }

    @Test
    fun `when start, then block screen`() = runTest {
        // given
        val obs = viewModel.contentLoading.test()

        // when

        // then
        advanceUntilIdle()

        obs.assertValueHistory(
            true,
            false,
        )
    }

    @Parameters(method = "provide1")
    @Test
    fun `when start, given API success, then update items`(items: List<NewsItem>) = runTest {
        // given
        val obs = viewModel.items.test()
        coEvery { useCase.retrieveNews() } answers { items }

        // when

        // then
        advanceUntilIdle()

        obs.assertValueHistory(items)
    }

    fun provide1() = listOf(
        createListNewsItems()
    )

    @Test
    fun `when start, given API error, then report error and do not crash`() = runTest {
        // given
        val obs = viewModel.error.test()
        coEvery { useCase.retrieveNews() } answers { error("") }

        // when

        // then
        advanceUntilIdle()

        obs.assertValueHistory(
            true
        )
    }

    @Test
    fun `when user refresh, initiate data fetch`() = runTest {
        // given
        val obs = viewModel.contentLoading.test()

        // when
        viewModel.refreshTriggered()

        // then
        advanceUntilIdle()

        coVerify(exactly = 2) { useCase.retrieveNews() }
        obs.assertValueHistory(
            true,
            false,
            true,
            false,
        )
    }

    @Parameters(method = "provide2")
    @Test
    fun `when user clicked on news, given correct url links, then emit navigation event once`(item: NewsItem) =
        runTest {
            // given
            val obs = viewModel.error.test()

            // when
            viewModel.openLinkEvent.test {
                viewModel.userClickedOn(item)

                // then
                Assert.assertEquals(item.news_link, awaitItem())
                cancelAndIgnoreRemainingEvents()
            }

            advanceUntilIdle()
            obs.assertNoValue()
        }

    fun provide2() = createListNewsItems(count = 1, newsLink = "https://goodplace.nice")

    @Parameters(method = "provide3")
    @Test
    fun `when user clicked on news, given bad url links, then emit error event once`(item: NewsItem) =
        runTest {
            // given
            val obs = viewModel.error.test()

            // when
            viewModel.openLinkEvent.test {
                viewModel.userClickedOn(item)
                // then
                expectNoEvents()
            }

            advanceUntilIdle()
            obs.assertValueHistory(
                true,
            )
        }

    fun provide3() = createListNewsItems(count = 1, newsLink = "https+ssh://goodplace.nice")
}
