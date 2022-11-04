package com.pratclot.themoviedb

import javax.inject.Inject

class StringToYearConverter @Inject constructor() {

    fun getYearFrom(it: String): String {
        return it.take(4).takeIf { it.length == 4 && it.all { it.isDigit() } } ?: "2022"
    }
}
