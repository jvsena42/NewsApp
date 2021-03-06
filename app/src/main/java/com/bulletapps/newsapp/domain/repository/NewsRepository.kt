package com.bulletapps.newsapp.domain.repository

import com.bulletapps.newsapp.data.model.Article
import com.bulletapps.newsapp.data.model.NewsResponse
import com.bulletapps.newsapp.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNewsHeadlines(country: String, page: Int):Resource<NewsResponse>
    suspend fun getSearchedNews(country:String,searchQuery:String,page: Int):Resource<NewsResponse>

    suspend fun saveNews(article: Article)
    suspend fun deleteNews(article: Article)
    fun getSavedNews(): Flow<List<Article>>
}