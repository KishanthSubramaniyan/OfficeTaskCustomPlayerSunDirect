package com.sundirect.customplayer.datalayer.dto

import com.google.gson.annotations.SerializedName

data class ContinueWatching(
    @SerializedName("id")
    val id: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("thumbnail")
    val thumbnail: String,

    @SerializedName("videoUrl")
    val videoUrl: String,

    @SerializedName("progress")
    val progress: Double
)
