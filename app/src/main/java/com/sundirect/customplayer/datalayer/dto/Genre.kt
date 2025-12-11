package com.sundirect.customplayer.datalayer.dto

import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("id")
    val id: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("icon")
    val icon: String
)
