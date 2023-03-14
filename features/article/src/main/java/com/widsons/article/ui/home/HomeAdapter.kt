package com.widsons.article.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.widsons.article.R
import com.widsons.article.data.model.Article
import com.widsons.ui.adapter.BaseAdapter
import com.widsons.ui.adapter.BaseViewHolder

class HomeAdapter(items: List<Article> = listOf()) :
    BaseAdapter<HomeAdapter.HomeViewHolder, Article>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.article_view_holder,
                parent,
                false
            )
        )
    }

    inner class HomeViewHolder(itemView: View) : BaseViewHolder(itemView) {

        val imageViewArticle : ImageView = itemView.findViewById(R.id.imageViewArticle)
        val textViewTitle : TextView = itemView.findViewById(R.id.textViewTitle)
        val textViewDate : TextView = itemView.findViewById(R.id.textViewDate)


        override fun bind(item: Any?, position: Int) {
            if(item is Article) {
                Glide.with(itemView).load(item.image).into(imageViewArticle)
                textViewDate.setText(item.createdAt)
                textViewTitle.setText(item.title)
            }
        }
    }
}