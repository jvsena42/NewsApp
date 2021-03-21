package com.bulletapps.newsapp.domain.repository

import com.bulletapps.newsapp.data.model.Article
import com.bulletapps.newsapp.data.model.NewsResponse
import com.bulletapps.newsapp.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNewsHeadlines():Resource<NewsResponse>
    suspend fun getSearchedNews(searchQuery:String):Resource<NewsResponse>

    suspend fun saveNews(article: Article)
    suspend fun deleteNews(article: Article)
    fun getSavedNews(): Flow<List<Article>>
}