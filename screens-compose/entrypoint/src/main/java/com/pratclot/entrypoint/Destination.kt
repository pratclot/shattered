package com.pratclot.entrypoint

sealed class Destination(val id: String)

object EntryPoint : Destination("EntryPoint")
object SteamStore : Destination("SteamStore")
object News : Destination("News")
object Movies : Destination("Movies")
object Scout24 : Destination("Scout24")
