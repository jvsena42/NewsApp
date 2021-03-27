package com.bulletapps.newsapp.data.repository.DataSourceImpl

import com.bulletapps.newsapp.data.api.NewsAPIService
import com.bulletapps.newsapp.data.model.NewsResponse
import com.bulletapps.newsapp.data.repository.dataSource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
        private val newsAPIService: NewsAPIService,
        private val country:String,
        private val page:Int
): NewsRemoteDataSource {
    override suspend fun getTopHeadLines(): Response<NewsResponse> {
        return newsAPIService.getTopHeadlines(country,page)
    }

}