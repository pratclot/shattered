package com.pratclot.common

import javax.inject.Inject

private const val HTTPS = "https://"
private const val SPACE = ' '

/**
 * At first, this was intended to be a wrapper around [URLUtil].
 *
 * At second, it turned out this is still impossible to test without Robolectric.
 *
 * So, the logic is simply copied from [URLUtil.isHttpsUrl()] method :big_smiley:
 */
class UriValidator @Inject constructor() {
    fun check(link: String): Boolean {
        val result = link.isNotEmpty() &&
            link.length > 7 &&
            link.substring(0, 8).equals(HTTPS, ignoreCase = true) &&
            !link.contains(SPACE)
        return result
    }
}
