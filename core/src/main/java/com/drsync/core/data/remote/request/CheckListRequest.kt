package com.drsync.core.data.remote.request

import com.google.gson.annotations.SerializedName

data class CheckListRequest(
    @field:SerializedName("name")
    val name: String
)
