package com.example.flickr.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flickr.R
import com.example.flickr.data.pics
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.pic.view.*


class picsAdapter(val photos: MutableList<pics> = mutableListOf()) : RecyclerView.Adapter<picsAdapter.PhotosViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        return PhotosViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.pic,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int
    {
        return photos.size
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    inner class PhotosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(photo: pics) {
            Picasso.get().load(photo.url).into(itemView.imageView)
        }
    }
}