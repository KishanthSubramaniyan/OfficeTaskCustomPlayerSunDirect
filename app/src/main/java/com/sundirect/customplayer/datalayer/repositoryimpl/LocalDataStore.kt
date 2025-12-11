package com.sundirect.customplayer.datalayer.repositoryimpl

import android.content.Context
import com.google.gson.Gson
import com.sundirect.customplayer.datalayer.dto.HomeResponse
import com.sundirect.customplayer.domainlayer.repositoryinterface.VideoRepository
import jakarta.inject.Inject

class LocalDataStore @Inject constructor(
    private val context: Context
): VideoRepository {

    override fun getHomeData(): HomeResponse {
        val json = context.assets.open("videos.json").bufferedReader().use { it.readText() }
        return Gson().fromJson(json, HomeResponse::class.java)
    }
}