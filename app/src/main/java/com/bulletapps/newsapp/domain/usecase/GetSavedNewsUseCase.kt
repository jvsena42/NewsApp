package com.bulletapps.newsapp.domain.usecase

import com.bulletapps.newsapp.domain.repository.NewsRepository

class GetSavedNewsUseCase(private val newsRepository: NewsRepository) {
}