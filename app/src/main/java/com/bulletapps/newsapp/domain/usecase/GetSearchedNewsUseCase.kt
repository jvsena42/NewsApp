package com.bulletapps.newsapp.domain.usecase

import com.bulletapps.newsapp.domain.repository.NewsRepository

class GetSearchedNewsUseCase(private val newsRepository: NewsRepository) {
}