package com.bulletapps.newsapp.data.db

import androidx.room.*
import com.bulletapps.newsapp.data.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article:Article)

    @Query("SELECT * FROM articles")
    fun getAllArticles():Flow<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)

}