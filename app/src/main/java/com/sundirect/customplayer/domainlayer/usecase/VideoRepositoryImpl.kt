package com.sundirect.customplayer.domainlayer.usecase

import com.sundirect.customplayer.datalayer.dto.HomeResponse
import com.sundirect.customplayer.datalayer.repositoryimpl.LocalDataStore
import com.sundirect.customplayer.domainlayer.repositoryinterface.VideoRepository
import jakarta.inject.Inject

class VideoRepositoryImpl@Inject constructor(
    private val videoRepositoryImpl: LocalDataStore
) : VideoRepository {

    override fun getHomeData(): HomeResponse {
        return videoRepositoryImpl.getHomeData()
    }
}