package com.drsync.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class CheckList(
    @field:SerializedName("name")
    val name: String
)