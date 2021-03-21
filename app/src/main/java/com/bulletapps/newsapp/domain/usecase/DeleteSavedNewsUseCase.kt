package com.bulletapps.newsapp.domain.usecase

import com.bulletapps.newsapp.domain.repository.NewsRepository

class DeleteSavedNewsUseCase(private val newsRepository: NewsRepository) {
}