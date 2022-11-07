package com.pratclot.themoviedb

import javax.inject.Inject

class MovieUseCase @Inject constructor(
    private val moviesRepo: MoviesRepo,
) {
    suspend operator fun invoke(id: Int): Movie = moviesRepo.getMovieById(id)
}
