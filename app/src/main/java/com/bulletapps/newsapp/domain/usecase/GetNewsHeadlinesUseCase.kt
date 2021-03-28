package com.bulletapps.newsapp.domain.usecase

import com.bulletapps.newsapp.data.model.NewsResponse
import com.bulletapps.newsapp.data.util.Resource
import com.bulletapps.newsapp.domain.repository.NewsRepository

class GetNewsHeadlinesUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(country: String, page: Int):Resource<NewsResponse>{
        return newsRepository.getNewsHeadlines(country, page)
    }
}