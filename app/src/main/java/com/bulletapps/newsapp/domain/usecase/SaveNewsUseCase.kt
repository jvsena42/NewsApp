package com.bulletapps.newsapp.domain.usecase

import com.bulletapps.newsapp.domain.repository.NewsRepository

class SaveNewsUseCase(private val newsRepository: NewsRepository) {
}