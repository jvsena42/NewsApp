package com.bulletapps.newsapp.data.model


import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("articles")
    var articles: List<Article>?,
    @SerializedName("status")
    var status: String?,
    @SerializedName("totalResults")
    var totalResults: Int?
)