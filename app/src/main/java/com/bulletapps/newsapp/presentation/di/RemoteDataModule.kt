package com.bulletapps.newsapp.presentation.di

import com.bulletapps.newsapp.data.api.NewsAPIService
import com.bulletapps.newsapp.data.repository.datasourceimpl.NewsRemoteDataSourceImpl
import com.bulletapps.newsapp.data.repository.dataSource.NewsRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun providesNewsRemoteDataSource(newsAPIService: NewsAPIService):NewsRemoteDataSource{
        return NewsRemoteDataSourceImpl(newsAPIService)
    }
}