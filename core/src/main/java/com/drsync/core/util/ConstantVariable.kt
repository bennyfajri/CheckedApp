package com.drsync.core.util

import androidx.datastore.preferences.core.stringPreferencesKey
import com.drsync.core.data.remote.response.Token

object ConstantVariable {
    const val TAG = "Response::::::"

    const val REGISTER_OK = 2000
    const val LOGIN_OK = 2110

    val KEY_TOKEN = stringPreferencesKey("token")

    val Token.tokenBearer: String get() = "Bearer ${this.token}"
}