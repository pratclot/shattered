package com.pratclot

import com.pratclot.common.UriValidator
import junitparams.JUnitParamsRunner
import junitparams.Parameters
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(JUnitParamsRunner::class)
internal class UriValidatorTest : BaseTest() {

    private val uriValidator = UriValidator()

    @Parameters(method = "provide1")
    @Test
    fun `when wrong uri, then return false`(link: String) {
        // given

        // when

        // then
        Assert.assertEquals(false, uriValidator.check(link))
    }

    fun provide1() = listOf(
        "",
        "htts://",
        "123 https://",
        "http://incorrect link thank you",
        "https://incorrect link thank you",
        "https+ssh://incorrect link thank you",
    )

    @Parameters(method = "provide2")
    @Test
    fun `when correct uri, then return true`(link: String) {
        // given

        // when

        // then
        Assert.assertEquals(true, uriValidator.check(link))
    }

    fun provide2() = listOf(
        "https://correct.com",
        "https://correct.com/and/also?valid=true",
    )
}
