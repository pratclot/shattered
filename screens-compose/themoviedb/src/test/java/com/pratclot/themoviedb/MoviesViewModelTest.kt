package com.pratclot.themoviedb

import app.cash.turbine.test
import coil.ImageLoader
import com.pratclot.BaseTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MoviesViewModelTest : BaseTest() {

    private val useCase: MoviesUseCase = mockk()
    private val useCaseMovie: MovieUseCase = mockk()
    private val imageLoader: ImageLoader = mockk()
    private val logger: LoggingWrapper = mockk()

    private lateinit var viewModel: MoviesViewModel

    @Before
    fun setup() {
        viewModel = MoviesViewModel(
            useCase,
            useCaseMovie,
            imageLoader,
            logger,
        )
    }

    @Test
    fun `when started interacts with API`() = runTest {

        advanceUntilIdle()
        coVerify { useCase() }
    }

    @Test
    fun `when error on start show error`() = runTest {

        coEvery { useCase() } answers { error("") }

        viewModel.errorEvent.test {

            Assert.assertFalse(awaitItem())
            Assert.assertTrue(awaitItem())
            Assert.assertFalse(awaitItem())
        }
    }

    @Test
    fun `when one movie errors show error`() = runTest {

        coEvery { useCase() } answers { listOfFakeMovies() }
        coEvery { useCaseMovie(any()) } answers { error("") }

        viewModel.errorEvent.test {

            Assert.assertFalse(awaitItem())

            viewModel.clicked(fakeMovieUI())

            Assert.assertTrue(awaitItem())
            Assert.assertFalse(awaitItem())
        }
    }

    @Test
    fun `when selected a movie perform navigation`() = runTest {

        coEvery { useCase() } answers { listOfFakeMovies() }
        coEvery { useCaseMovie(any()) } answers { fakeMovie() }

        viewModel.navigationEvent.test {

            Assert.assertFalse(awaitItem())

            viewModel.clicked(fakeMovieUI())

            Assert.assertTrue(awaitItem())
        }
    }

    @Test
    fun `show loading indicator when refreshed`() = runTest {

        coEvery { useCase() } answers { listOfFakeMovies() }

        viewModel.loadingEvent.test {

            Assert.assertFalse(awaitItem())
            Assert.assertTrue(awaitItem())
            Assert.assertFalse(awaitItem())

            viewModel.userRefreshed()

            Assert.assertTrue(awaitItem())
            Assert.assertFalse(awaitItem())
        }
    }
}
