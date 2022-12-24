package com.drsync.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class User(
    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("password")
    val password: String,

    @field:SerializedName("username")
    val username: String,

    @field:SerializedName("token")
    val token: String
)
