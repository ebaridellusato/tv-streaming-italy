package com.tvstreaming.italy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tvstreaming.italy.R
import com.tvstreaming.italy.database.Channel

class ChannelAdapter(
    private val onChannelClick: (Channel) -> Unit,
    private val onFavoriteClick: (Channel) -> Unit,
    private val onDeleteClick: ((Channel) -> Unit)? = null
) : ListAdapter<Channel, ChannelAdapter.ChannelViewHolder>(Diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelViewHolder =
        ChannelViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_channel, parent, false)
        )

    override fun onBindViewHolder(holder: ChannelViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ChannelViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val tvName: TextView = v.findViewById(R.id.tvChannelName)
        private val tvCat: TextView = v.findViewById(R.id.tvChannelCategory)
        private val ivLogo: ImageView = v.findViewById(R.id.ivChannelLogo)
        private val ivFav: ImageView = v.findViewById(R.id.ivFavorite)
        private val ivDel: ImageView = v.findViewById(R.id.ivDelete)

        fun bind(ch: Channel) {
            tvName.text = ch.name
            tvCat.text = ch.category
            ivLogo.setImageResource(R.drawable.ic_tv_placeholder)
            ivFav.setImageResource(
                if (ch.isFavorite) R.drawable.ic_star_filled else R.drawable.ic_star_outline
            )
            ivDel.visibility = if (ch.isPreloaded) View.GONE else View.VISIBLE

            v.setOnClickListener { onChannelClick(ch) }
            ivFav.setOnClickListener { onFavoriteClick(ch) }
            ivDel.setOnClickListener { onDeleteClick?.invoke(ch) }
        }
    }

    object Diff : DiffUtil.ItemCallback<Channel>() {
        override fun areItemsTheSame(a: Channel, b: Channel) = a.id == b.id
        override fun areContentsTheSame(a: Channel, b: Channel) = a == b
    }
}
