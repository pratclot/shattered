package com.pratclot

import android.util.Log

const val TAG = ">>>>>>>>>>>>>>>>>>>> "

inline fun <reified T : Any> T.logE(ex: Throwable) =
    Log.e(TAG, ex.stackTraceToString())

inline fun <reified T : Any> T.throwE(ex: Throwable) {
    logE(ex)
    throw ex
}
