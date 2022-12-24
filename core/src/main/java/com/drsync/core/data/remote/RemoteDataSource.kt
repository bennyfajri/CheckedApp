package com.drsync.core.data.remote

import android.util.Log
import com.drsync.core.data.Resource
import com.drsync.core.data.remote.network.ApiService
import com.drsync.core.data.remote.request.LoginRequest
import com.drsync.core.data.remote.request.RegisterRequest
import com.drsync.core.data.remote.response.ServerResponse
import com.drsync.core.util.ConstantVariable.LOGIN_OK
import com.drsync.core.util.ConstantVariable.REGISTER_OK
import com.drsync.core.util.ConstantVariable.TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    fun loginUser(loginRequest: LoginRequest) = flow<Resource<ServerResponse>> {
        emit(Resource.Loading())
        val response = apiService.loginUser(loginRequest)
        response.let {
            if (it.statusCode == LOGIN_OK) emit(Resource.Success(it))
            else emit(Resource.Error(it.message))
        }
    }.catch {
        Log.d(TAG, "loginUser: ${it.message}")
        emit(Resource.Error(it.message ?: ""))
    }.flowOn(Dispatchers.IO)

    fun registerUser(registerRequest: RegisterRequest) = flow<Resource<ServerResponse>> {
        emit(Resource.Loading())
        val response = apiService.registerUser(registerRequest)
        response.let {
            if (it.statusCode == REGISTER_OK) emit(Resource.Success(it))
            else emit(Resource.Error(it.message))
        }
    }.catch {
        Log.d(TAG, "registerUser: ${it.message}")
        emit(Resource.Error(it.message ?: ""))
    }.flowOn(Dispatchers.IO)
}