package com.drsync.core.data

import com.drsync.core.data.local.LocalDataSource
import com.drsync.core.data.remote.RemoteDataSource
import com.drsync.core.data.remote.request.CheckListRequest
import com.drsync.core.data.remote.request.LoginRequest
import com.drsync.core.data.remote.request.RegisterRequest
import com.drsync.core.data.remote.response.Token
import com.drsync.core.data.remote.response.User
import javax.inject.Inject

class ChekedAppRepository @Inject constructor(
    private val localData: LocalDataSource,
    private val remoteData: RemoteDataSource
) {
    fun getToken() = localData.getToken()

    suspend fun saveToken(token: Token) = localData.saveToken(token)

    suspend fun deleteToken() = localData.deleteToken()

    fun loginUser(request: LoginRequest) = remoteData.loginUser(request)

    fun registerUser(request: RegisterRequest) = remoteData.registerUser(request)

    fun insertCheckList(header: String, request: CheckListRequest) = remoteData.insertCheckList(header, request)

    fun getCheckList(header: String) = remoteData.getCheckList(header)
}