package com.bulletapps.newsapp.presentation.di

import android.app.Application
import com.bulletapps.newsapp.domain.usecase.GetNewsHeadlinesUseCase
import com.bulletapps.newsapp.presentation.viewmodel.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun providesNewsViewModelFactory(
        application: Application,
        getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase
    ):NewsViewModelFactory{
        return NewsViewModelFactory(application,getNewsHeadlinesUseCase)
    }
}