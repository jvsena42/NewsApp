package com.bulletapps.newsapp.domain.usecase

import com.bulletapps.newsapp.data.model.NewsResponse
import com.bulletapps.newsapp.data.util.Resource
import com.bulletapps.newsapp.domain.repository.NewsRepository

class GetSearchedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(country:String,searchQuery:String,page:Int): Resource<NewsResponse> {
        return newsRepository.getSearchedNews(country,searchQuery,page)
    }
}