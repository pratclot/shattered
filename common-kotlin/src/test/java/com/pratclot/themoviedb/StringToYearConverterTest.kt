package com.pratclot.themoviedb

import junitparams.JUnitParamsRunner
import junitparams.Parameters
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(JUnitParamsRunner::class)
class StringToYearConverterTest {

    private val converter = StringToYearConverter()

    @Parameters(method = "provide1")
    @Test
    fun `return correct year from date string`(str: String, year: String) {
        Assert.assertEquals(year, converter.getYearFrom(str))
    }

    fun provide1() = setOf(
        listOf("2022-10-06", "2022"),
        listOf("2022", "2022"),
        listOf("10-06", "2022"),
    )
}
