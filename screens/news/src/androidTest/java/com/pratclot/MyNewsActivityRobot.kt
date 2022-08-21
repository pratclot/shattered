package com.pratclot

fun myNews(function: MyNewsActivityRobot.() -> Unit) =
    MyNewsActivityRobot().apply(function)

class MyNewsActivityRobot
