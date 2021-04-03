package com.bulletapps.newsapp.data.repository

import com.bulletapps.newsapp.data.model.Article
import com.bulletapps.newsapp.data.model.NewsResponse
import com.bulletapps.newsapp.data.repository.dataSource.NewsRemoteDataSource
import com.bulletapps.newsapp.data.util.Resource
import com.bulletapps.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(
        private val newsRemoteDataSource: NewsRemoteDataSource
):NewsRepository {
    override suspend fun getNewsHeadlines(country: String, page: Int): Resource<NewsResponse> {
        return responseToResource(newsRemoteDataSource.getTopHeadLines(country, page))
    }

    private fun responseToResource(response: Response<NewsResponse>):Resource<NewsResponse>{
        if (response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun getSearchedNews(
        country: String,
        searchQuery: String,
        page: Int
    ): Resource<NewsResponse> {
        return responseToResource(newsRemoteDataSource.getSearchedNews(country,searchQuery,page))
    }

    override suspend fun saveNews(article: Article) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNews(article: Article) {
        TODO("Not yet implemented")
    }

    override fun getSavedNews(): Flow<List<Article>> {
        TODO("Not yet implemented")
    }
}