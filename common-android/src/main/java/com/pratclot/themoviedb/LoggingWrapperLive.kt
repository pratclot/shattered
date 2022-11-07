package com.pratclot.themoviedb

import android.util.Log
import javax.inject.Inject

private const val TAG = "LoggingWrapperLive >> "

class LoggingWrapperLive @Inject constructor() : LoggingWrapper {
    override fun d(msg: String) {
        Log.d(com.pratclot.themoviedb.TAG, msg)
    }

    override fun e(msg: String) {
        Log.e(com.pratclot.themoviedb.TAG, msg)
    }
}
