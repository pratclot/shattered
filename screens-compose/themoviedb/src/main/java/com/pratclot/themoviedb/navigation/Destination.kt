package com.pratclot.themoviedb.navigation

sealed class Destination(val id: String)

object EntryPoint : Destination("EntryPoint")
object MovieScreen : Destination("Movie")
