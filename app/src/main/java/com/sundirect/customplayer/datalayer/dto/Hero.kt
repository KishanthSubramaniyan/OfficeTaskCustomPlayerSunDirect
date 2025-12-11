package com.sundirect.customplayer.datalayer.dto

import com.google.gson.annotations.SerializedName

data class Hero(
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("thumbnail")
    val thumbnail: String,

    @SerializedName("videoUrl")
    val videoUrl: String
)
