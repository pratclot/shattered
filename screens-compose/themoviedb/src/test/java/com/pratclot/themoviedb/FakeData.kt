package com.pratclot.themoviedb

import com.pratclot.themoviedb.model.convert

fun fakeMovie(): Movie = Movie(title = "", year = "", pictureUrl = "", overview = "", id = 0)

fun fakeMovieUI() = fakeMovie().convert()

fun listOfFakeMovies() = listOf(fakeMovie())
