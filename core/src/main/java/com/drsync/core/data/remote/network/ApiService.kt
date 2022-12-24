package com.drsync.core.data.remote.network

import com.drsync.core.data.remote.request.LoginRequest
import com.drsync.core.data.remote.request.RegisterRequest
import com.drsync.core.data.remote.response.ServerResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("register")
    suspend fun registerUser(
        @Body request: RegisterRequest
    ): ServerResponse

    @POST("login")
    suspend fun loginUser(
        @Body request: LoginRequest
    ): ServerResponse

}