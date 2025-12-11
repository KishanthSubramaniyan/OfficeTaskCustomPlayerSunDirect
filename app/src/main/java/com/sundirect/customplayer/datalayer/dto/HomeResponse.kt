package com.sundirect.customplayer.datalayer.dto

import com.google.gson.annotations.SerializedName

data class HomeResponse(
    @SerializedName("hero")
    val hero: List<Hero>,

    @SerializedName("continueWatching")
    val continueWatching: List<ContinueWatching>,

    @SerializedName("trending")
    val trending: List<Trending>,

    @SerializedName("genres")
    val genres: List<Genre>
)
