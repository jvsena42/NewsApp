package com.bulletapps.newsapp.domain.usecase

import com.bulletapps.newsapp.data.model.Article
import com.bulletapps.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedNewsUseCase(private val newsRepository: NewsRepository) {
    fun execute(): Flow<List<Article>> {
        return newsRepository.getSavedNews()
    }
}