package com.sundirect.customplayer.domainlayer.repositoryinterface

import com.sundirect.customplayer.datalayer.dto.HomeResponse

interface VideoRepository {
    fun getHomeData(): HomeResponse
}