package com.bulletapps.newsapp.data.repository.datasourceimpl

import com.bulletapps.newsapp.data.db.ArticleDAO
import com.bulletapps.newsapp.data.model.Article
import com.bulletapps.newsapp.data.repository.dataSource.NewsLocalDataSource

class NewsLocalDataSourceImpl(
    private val articleDAO: ArticleDAO
):NewsLocalDataSource {
    override suspend fun saveArticleToDB(article:Article) {
        articleDAO.insert(article)
    }
}