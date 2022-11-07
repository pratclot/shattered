package com.pratclot.api

import javax.inject.Inject

const val API_HOST_IMAGES = "https://image.tmdb.org/t/p/w500"

class PicturePathEvaluator @Inject constructor() {
    operator fun invoke(s: String): String {
        return API_HOST_IMAGES + s
    }
}
