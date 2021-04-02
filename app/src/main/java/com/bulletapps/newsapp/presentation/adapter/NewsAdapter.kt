package com.bulletapps.newsapp.presentation.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bulletapps.newsapp.data.model.Article
import com.bulletapps.newsapp.data.util.loadImage
import com.bulletapps.newsapp.databinding.ListNewsBinding

class NewsAdapter:RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private val callback = object :DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ListNewsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.bind( article)
    }

    override fun getItemCount() = differ.currentList.size

    inner class NewsViewHolder(private val binding:ListNewsBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(article: Article){
            binding.tvTitle.text = article.title
            binding.tvDescription.text = article.description
            binding.tvPublishedAt.text = article.publishedAt
            binding.tvSource.text = article.source?.name
            binding.ivArticleImage.loadImage(article.urlToImage)

            binding.root.setOnClickListener{
                onItemClickListener?.let {
                    it(article)
                }
            }
        }
    }

    private var onItemClickListener:((Article)->Unit)? = null

    fun setOnItemClickListener(listener:(Article)->Unit){
        onItemClickListener = listener
    }
}