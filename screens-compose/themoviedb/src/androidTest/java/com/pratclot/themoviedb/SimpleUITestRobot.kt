package com.pratclot.themoviedb

fun uiTestRobot(function: SimpleUITestRobot.() -> Unit) =
    SimpleUITestRobot().apply(function)

class SimpleUITestRobot
