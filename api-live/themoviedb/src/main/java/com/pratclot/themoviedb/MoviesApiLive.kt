package com.pratclot.themoviedb

import com.pratclot.api.MoviesApi
import com.pratclot.dto.MovieDto
import com.pratclot.dto.MoviesDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val API_HOST_MOVIES = "https://api.themoviedb.org/"

interface MoviesApiLive : MoviesApi {

    @GET("3/discover/movie")
    override suspend fun getMovies(): MoviesDto

    @GET("3/movie/{id}")
    override suspend fun getMovieById(@Path("id") id: Int): MovieDto
}
