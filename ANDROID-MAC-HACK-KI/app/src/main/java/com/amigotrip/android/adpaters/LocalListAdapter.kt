package com.amigotrip.android.adpaters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amigotrip.android.custom.ZoomOutPageTransformer
import com.amigotrip.android.datas.Article
import com.amigotrip.anroid.R
import kotlinx.android.synthetic.main.row_locals.view.*

/**
 * Created by Zimincom on 2017. 11. 12..
 */
class LocalListAdapter : RecyclerView.Adapter<LocalListAdapter.ViewHolder>(){

    private val localList = arrayListOf<Article>()
    private lateinit var localListItemClickListener: OnLocalListItemClickListener


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view =
                LayoutInflater.from(parent?.context)
                        .inflate(R.layout.row_locals, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(localList[position])
    }

    override fun getItemCount(): Int {
        return localList.size
    }

    fun setOnLocalItemClickListener(listener: OnLocalListItemClickListener) {
        this.localListItemClickListener = listener
    }

    fun addAll(list: List<Article>) {
        localList.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(article: Article) {
            itemView.tv_tags.text = article.location
            itemView.tv_name.text = article.writer.name
            itemView.tv_message_preview.text = article.contents

            itemView.pager_preview.adapter = PreviewPagerAdapter()
            itemView.pager_preview.setPageTransformer(true, ZoomOutPageTransformer())
            itemView.setOnClickListener { view -> localListItemClickListener.onLocalItemClick(view, article) }
        }
    }

    interface OnLocalListItemClickListener {
        fun onLocalItemClick(view: View?, article: Article)
    }

}