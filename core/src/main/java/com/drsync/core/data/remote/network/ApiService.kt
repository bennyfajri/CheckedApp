package com.drsync.core.data.remote.network

import com.drsync.core.data.remote.request.CheckListRequest
import com.drsync.core.data.remote.request.LoginRequest
import com.drsync.core.data.remote.request.RegisterRequest
import com.drsync.core.data.remote.response.CheckListResponse
import com.drsync.core.data.remote.response.ServerResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
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

    @POST("checklist")
    suspend fun insertCheckList(
        @Header("Authorization") header: String,
        @Body request: CheckListRequest
    ): CheckListResponse

    @GET("checklist")
    suspend fun getCheckList(
        @Header("Authorization") header: String
    ): CheckListResponse

}