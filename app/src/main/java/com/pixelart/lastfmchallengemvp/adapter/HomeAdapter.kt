package com.pixelart.lastfmchallengemvp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pixelart.lastfmchallengemvp.R
import com.pixelart.lastfmchallengemvp.common.GlideApp
import com.pixelart.lastfmchallengemvp.databinding.RvhomeLayoutBinding
import com.pixelart.lastfmchallengemvp.model.Album
import kotlinx.android.synthetic.main.rvhome_layout.view.*

class HomeAdapter(private val listener: OnItemClickedListener):ListAdapter<Album, HomeAdapter.ViewHolder>(DIFF_CALLBACK){

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        context = parent.context
        val view: RvhomeLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.rvhome_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        val album = getItem(position)

        if (album != null){
            holder.itemView.apply {
                tvAlbumName.text = album.name
                tvArtist.text = album.artist

                GlideApp.with(context)
                    .load(album.image[1].text)
                    .placeholder(R.drawable.placeholder_albumart)
                    .error(R.drawable.placeholder_albumart)
                    .override(80, 80)
                    .into(ivAlbumImage)
            }
        }

        holder.itemView.setOnClickListener { listener.onItemClicked(position) }
    }


    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Album> = object : DiffUtil.ItemCallback<Album>() {
            override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
                return oldItem == newItem
            }

        }
    }

    class ViewHolder(binder: RvhomeLayoutBinding): RecyclerView.ViewHolder(binder.root)

    interface OnItemClickedListener{
        fun onItemClicked(position: Int)
    }
}