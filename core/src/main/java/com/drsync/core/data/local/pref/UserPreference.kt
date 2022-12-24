package com.drsync.core.data.local.pref

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.drsync.core.data.remote.response.Token
import com.drsync.core.util.ConstantVariable.KEY_TOKEN
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.tokenStore by preferencesDataStore("token")

class UserPreference(
    context: Context
) {
    private val userDataStore = context.tokenStore

    fun getToken(): Flow<Token> {
        return userDataStore.data.map { pref ->
            Token(
                pref[KEY_TOKEN] ?: "",

            )
        }
    }

    suspend fun saveToken(data: Token){
        userDataStore.edit { pref ->
            pref[KEY_TOKEN] = data.token
        }
    }

    suspend fun deleteToken() {
        userDataStore.edit { pref ->
            pref.clear()
        }
    }
}