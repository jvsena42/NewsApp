package com.bulletapps.newsapp.presentation.di

import com.bulletapps.newsapp.domain.repository.NewsRepository
import com.bulletapps.newsapp.domain.usecase.GetNewsHeadlinesUseCase
import com.bulletapps.newsapp.domain.usecase.GetSavedNewsUseCase
import com.bulletapps.newsapp.domain.usecase.GetSearchedNewsUseCase
import com.bulletapps.newsapp.domain.usecase.SaveNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun providesGetNewsHeadlinesUseCase(
        newsRepository: NewsRepository
    ):GetNewsHeadlinesUseCase{
        return GetNewsHeadlinesUseCase(newsRepository)
    }

    @Singleton
    @Provides
    fun providesGetSearchedNewsUseCase(
        newsRepository: NewsRepository
    ):GetSearchedNewsUseCase{
        return GetSearchedNewsUseCase(newsRepository)
    }

    @Singleton
    @Provides
    fun providesSaveNewsUseCase(
        newsRepository: NewsRepository
    ):SaveNewsUseCase{
        return SaveNewsUseCase(newsRepository)
    }

    @Singleton
    @Provides
    fun providesGetSavedNewsUseCase(
        newsRepository: NewsRepository
    ):GetSavedNewsUseCase{
        return GetSavedNewsUseCase(newsRepository)
    }
}