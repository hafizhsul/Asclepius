package com.dicoding.asclepius.view.result

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.asclepius.data.remote.response.ArticlesItem
import com.dicoding.asclepius.databinding.ListItemNewsBinding

class NewsAdapter: ListAdapter<ArticlesItem, NewsAdapter.MyViewHolder>(DIFF_CALLBACK) {

    class MyViewHolder(private val binding: ListItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(articles: ArticlesItem){
            binding.newsTitle.text = articles.title
            binding.newsDescription.text = articles.description
            if (articles.urlToImage != null) {
                Glide.with(binding.newsImage.context)
                    .load(articles.urlToImage)
                    .into(binding.newsImage)
            }

            binding.root.setOnClickListener {
                val intentUrl = Intent(Intent.ACTION_VIEW, Uri.parse(articles.url))
                binding.root.context.startActivity(intentUrl)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ListItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val articles = getItem(position)
        holder.bind(articles)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ArticlesItem>() {
            override fun areItemsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}