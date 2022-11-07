package com.pratclot.themoviedb

import com.pratclot.api.MoviesApi
import javax.inject.Inject

class MoviesRepo @Inject constructor(
    private val moviesApi: MoviesApi,
    private val converter: DtoToDomainConverter,
) {
    suspend fun getMovies(): List<Movie> = moviesApi.getMovies().results.map {
        converter.toMovie(it)
    }

    suspend fun getMovieById(id: Int): Movie = moviesApi.getMovieById(id).let {
        converter.toMovie(it)
    }
}
