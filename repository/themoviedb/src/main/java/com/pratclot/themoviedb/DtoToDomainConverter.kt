package com.pratclot.themoviedb

import com.pratclot.api.PicturePathEvaluator
import com.pratclot.dto.MovieDto
import com.pratclot.dto.MoviesResult
import javax.inject.Inject

class DtoToDomainConverter @Inject constructor(
    private val stringToYearConverter: StringToYearConverter,
    private val picturePathEvaluator: PicturePathEvaluator,
) {

    fun toMovie(moviesResult: MoviesResult) = moviesResult.run {
        Movie(
            title = title,
            year = release_date.let { stringToYearConverter.getYearFrom(it) },
            pictureUrl = poster_path.let { picturePathEvaluator(it) },
            overview = overview,
            id = id,
        )
    }

    fun toMovie(movieDto: MovieDto) = movieDto.run {
        Movie(
            title = title,
            year = release_date.let { stringToYearConverter.getYearFrom(it) },
            pictureUrl = poster_path.let { picturePathEvaluator(it) },
            overview = overview,
            id = id,
        )
    }
}
