package com.pratclot.news

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.load
import coil.transform.CircleCropTransformation
import com.pratclot.domain.NewsItem

class NewsAdapter(
    private val imageLoader: ImageLoader,
    private val newsItemClickListener: MyNewsActivity.NewsItemClickListener,
) :
    ListAdapter<NewsItem, NewsAdapter.NewsViewHolder>(Callback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = getItem(position)

        with(holder) {
            titleView.text = news.title
            newsView.loadNicely(news.image_url, imageLoader)
            resourceImage.loadNicely(news.resource_url, imageLoader)
            resourceName.text = news.resource_name
            itemView.setOnClickListener { newsItemClickListener(news) }
        }
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var titleView: TextView = itemView.findViewById(R.id.news_title)
        var newsView: ImageView = itemView.findViewById(R.id.news_view)
        var resourceImage: ImageView = itemView.findViewById(R.id.resource_icon)
        var resourceName: TextView = itemView.findViewById(R.id.resource_name)
    }
}

private class Callback<T : Any> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem::class == newItem::class
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }
}

fun ImageView.loadNicely(url: String, imageLoader: ImageLoader) = load(url, imageLoader) {
    crossfade(true)
    error(R.drawable.ic_baseline_broken_image_24)
    transformations(CircleCropTransformation())
}
