package com.pratclot.themoviedb

import javax.inject.Inject

class MoviesUseCase @Inject constructor(
    private val moviesRepo: MoviesRepo,
) {
    suspend operator fun invoke(): List<Movie> = moviesRepo.getMovies()
}
