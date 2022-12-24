package com.drsync.core.data.local

import com.drsync.core.data.local.pref.UserPreference
import com.drsync.core.data.remote.response.Token
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val userPref: UserPreference
){
    fun getToken() = userPref.getToken()

    suspend fun saveToken(token: Token) = userPref.saveToken(token)

    suspend fun deleteToken() = userPref.deleteToken()
}