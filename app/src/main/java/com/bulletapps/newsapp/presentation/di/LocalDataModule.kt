package com.bulletapps.newsapp.presentation.di

import com.bulletapps.newsapp.data.db.ArticleDAO
import com.bulletapps.newsapp.data.repository.dataSource.NewsLocalDataSource
import com.bulletapps.newsapp.data.repository.datasourceimpl.NewsLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Provides
    @Singleton
    fun provideLocalDataSource(articleDAO: ArticleDAO):NewsLocalDataSource{
        return NewsLocalDataSourceImpl(articleDAO)
    }
}