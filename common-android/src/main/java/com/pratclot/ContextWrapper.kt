package com.pratclot

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ContextWrapper @Inject constructor(
    @ApplicationContext val context: Context
)
