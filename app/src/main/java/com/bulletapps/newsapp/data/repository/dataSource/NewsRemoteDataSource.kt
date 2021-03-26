package com.bulletapps.newsapp.data.repository.dataSource

import com.bulletapps.newsapp.data.model.NewsResponse
import retrofit2.Response

interface NewsRemoteDataSource {
    suspend fun getTopHeadLines(): Response<NewsResponse>
}