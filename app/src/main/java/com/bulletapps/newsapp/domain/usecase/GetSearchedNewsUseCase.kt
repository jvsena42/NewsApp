package com.bulletapps.newsapp.domain.usecase

import com.bulletapps.newsapp.data.model.NewsResponse
import com.bulletapps.newsapp.data.util.Resource
import com.bulletapps.newsapp.domain.repository.NewsRepository

class GetSearchedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(searchQuery:String): Resource<NewsResponse> {
        return newsRepository.getSearchedNews(searchQuery)
    }
}