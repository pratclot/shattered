package com.pratclot.api

import com.pratclot.dto.MovieDto
import com.pratclot.dto.MoviesDto

interface MoviesApi {

    suspend fun getMovies(): MoviesDto

    suspend fun getMovieById(id: Int): MovieDto
}
