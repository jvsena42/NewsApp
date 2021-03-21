package com.bulletapps.newsapp.domain.usecase

import com.bulletapps.newsapp.data.model.Article
import com.bulletapps.newsapp.domain.repository.NewsRepository

class DeleteSavedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(article: Article) = newsRepository.deleteNews(article)
}