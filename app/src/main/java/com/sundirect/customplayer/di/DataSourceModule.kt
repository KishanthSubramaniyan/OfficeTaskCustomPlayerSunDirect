package com.sundirect.customplayer.di

import android.content.Context
import com.sundirect.customplayer.datalayer.repositoryimpl.LocalDataStore
import com.sundirect.customplayer.domainlayer.usecase.VideoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideLocalDataSource(
        @ApplicationContext context: Context
    ) : LocalDataStore {
        return LocalDataStore(context)
    }
}
