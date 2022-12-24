package com.drsync.core.util

import androidx.datastore.preferences.core.stringPreferencesKey

object ConstantVariable {
    const val TAG = "Response::::::"

    const val REGISTER_OK = 2000
    const val LOGIN_OK = 2110

    val KEY_TOKEN = stringPreferencesKey("token")
}