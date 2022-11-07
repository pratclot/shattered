package com.pratclot.themoviedb

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import com.pratclot.themoviedb.model.MovieUI
import com.pratclot.themoviedb.model.convert
import com.pratclot.themoviedb.model.matches
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

const val DEFAULT_ERROR_LIFETIME = 700L

@HiltViewModel
class MoviesViewModel @Inject constructor(
    val useCase: MoviesUseCase,
    val useCaseMovie: MovieUseCase,
    val imageLoader: ImageLoader,
    val logger: LoggingWrapper,
) : ViewModel() {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<MovieUI>>
        get() = _movies.map {
            it.map { it.convert() }
        }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private var _selected: Movie? = null
    val selected: MovieUI?
        get() = _selected?.convert()

    private val _navigationEvent = MutableStateFlow(false)
    val navigationEvent: StateFlow<Boolean>
        get() = _navigationEvent

    private val _errorEvent = MutableStateFlow(false)
    val errorEvent: StateFlow<Boolean>
        get() = _errorEvent

    private val _loadingEvent = MutableStateFlow(false)
    val loadingEvent: StateFlow<Boolean>
        get() = _loadingEvent

    init {
        userRefreshed()
    }

    private suspend fun getMovies() = runCatchingWithProgress {
        useCase().let { _movies.emit(it) }
    }

    fun clicked(movieUI: MovieUI) = viewModelScope.launch {
        loadMovie(movieUI)
    }

    private suspend fun loadMovie(movieUI: MovieUI) = runCatchingWithProgress {
        _movies.value.find { it.matches(movieUI) }
            ?.let { useCaseMovie(it.id) }
            ?.let {
                _selected = it
                openMovieScreen()
            }
    }

    private suspend fun handleError(it: Throwable) {
        if (it is CancellationException) throw it
        logger.e(it.stackTraceToString())
        _errorEvent.emit(true)
        delay(DEFAULT_ERROR_LIFETIME)
        _errorEvent.emit(false)
    }

    private suspend fun openMovieScreen() = _navigationEvent.emit(true)

    fun navigationComplete() = viewModelScope.launch {
        _navigationEvent.emit(false)
    }

    fun userRefreshed() = viewModelScope.launch {
        getMovies()
    }

    private suspend fun runCatchingWithProgress(block: suspend () -> Unit) = runCatching {
        _loadingEvent.emit(true)
        block()
    }
        .exceptionOrNull()?.let { handleError(it) }
        .also { _loadingEvent.emit(false) }
}
