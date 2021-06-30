package com.bulletapps.newsapp.data.repository.dataSource

import com.bulletapps.newsapp.data.model.Article

interface NewsLocalDataSource {
    suspend fun saveArticleToDB(article: Article)
}