package com.csepi.rssfeedreader.presentation.view.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.csepi.rssfeedreader.R
import com.csepi.rssfeedreader.data.model.Article
import com.squareup.picasso.Picasso

class FeedAdapter : RecyclerView.Adapter<FeedAdapter.ArticleItemViewHolder>() {

    var articles: List<Article>? = null
    var shareClickListener : OnShareClickListener? = null

    fun setData(data: List<Article>) {
        articles = data
        notifyDataSetChanged()
    }

    interface OnShareClickListener {
        fun onShareClick(position: Int, item: Article)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleItemViewHolder {
        val view = LayoutInflater.from(parent.context)?.inflate(R.layout.item_article, parent, false)
        return ArticleItemViewHolder(view!!)
    }

    override fun getItemCount(): Int {
        return articles?.size ?: 0
    }

    override fun onBindViewHolder(holder: ArticleItemViewHolder, position: Int) {
        articles?.let {
            val article = it[position]
            holder.articleHeader.text = article.articleTitle
            holder.articleDescription.text = article.description

            if (article.enclosures.size != 0 && article.enclosures[0].url.isNotEmpty()) {
                Picasso.get()
                        .load(article.enclosures[0].url)
                        .resize(100, 100)
                        .centerCrop()
                        .placeholder(holder.articleImage.context.resources
                                .getDrawable(R.drawable.image_placeholder))
                        .error(holder.articleImage.context.resources
                                .getDrawable(R.drawable.image_placeholder))
                        .into(holder.articleImage)
            } else {
                holder.articleImage.setImageResource(R.drawable.image_placeholder)
            }
        }
    }

    inner class ArticleItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val articleImage = view.findViewById(R.id.articleImage) as ImageView
        val articleHeader = view.findViewById(R.id.articleHeader) as TextView
        val articleDescription = view.findViewById(R.id.articleDescription) as TextView
        val shareButtonContainer = view.findViewById(R.id.shareButtonContainer) as RelativeLayout

        init {
            shareButtonContainer.setOnClickListener { _ ->
                articles?.let {
                    val position = layoutPosition
                    shareClickListener?.onShareClick(position, it[position])
                }
            }
        }
    }
}