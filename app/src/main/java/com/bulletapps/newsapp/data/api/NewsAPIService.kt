package com.bulletapps.newsapp.data.api

import com.bulletapps.newsapp.BuildConfig
import com.bulletapps.newsapp.data.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIService {

    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
            @Query("country")
            country:String,
            @Query("page")
            page:Int,
            @Query("apiKey")
            apiKey:String = BuildConfig.API_KEY,
    ):Response<NewsResponse>

    @GET("v2/top-headlines")
    suspend fun getSearchedTopHeadlines(
        @Query("country")
        country:String,
        @Query("q")
        searchQuery:String,
        @Query("page")
        page:Int,
        @Query("apiKey")
        apiKey:String = BuildConfig.API_KEY,
    ):Response<NewsResponse>
}