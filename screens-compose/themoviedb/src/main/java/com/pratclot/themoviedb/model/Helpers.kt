package com.pratclot.themoviedb.model

import com.pratclot.themoviedb.Movie

fun Movie.convert() = MovieUI(
    title = title,
    year = year,
    pictureUrl = pictureUrl,
    overview = overview,
)

fun Movie.matches(movieUI: MovieUI): Boolean {
    return title == movieUI.title && year == movieUI.year && pictureUrl == movieUI.pictureUrl
}
