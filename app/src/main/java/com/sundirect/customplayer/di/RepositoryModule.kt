package com.sundirect.customplayer.di

import com.sundirect.customplayer.datalayer.repositoryimpl.LocalDataStore
import com.sundirect.customplayer.domainlayer.repositoryinterface.VideoRepository
import com.sundirect.customplayer.domainlayer.usecase.VideoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindVideoRepository(
        impl: VideoRepositoryImpl
    ): VideoRepository
}