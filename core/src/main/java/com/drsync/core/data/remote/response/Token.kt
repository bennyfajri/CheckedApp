package com.drsync.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class Token(
    @field:SerializedName("token")
    val token: String
)