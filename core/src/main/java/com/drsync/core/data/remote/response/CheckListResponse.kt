package com.drsync.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class CheckListResponse(
    @field:SerializedName("statusCode")
    val statusCode: Int,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("errorMessage")
    val errorMessage: String? = null,

    @field:SerializedName("data")
    val data: List<CheckList>? = null
)
