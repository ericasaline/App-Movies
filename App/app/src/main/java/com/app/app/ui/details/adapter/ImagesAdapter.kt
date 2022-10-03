package com.app.app.ui.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.app.R
import com.app.app.databinding.ItemPhotosBinding
import com.app.app.model.ImageModel
import com.squareup.picasso.Picasso

class ImagesAdapter(private val images: List<ImageModel>)
    : RecyclerView.Adapter<ImagesAdapter.ViewHolder>() {

    class ViewHolder(biding: ItemPhotosBinding)
        : RecyclerView.ViewHolder(biding.root) {

        private val url = "https://image.tmdb.org/t/p/w500"
        private val img = biding.itemPhotos

        fun bind(image: ImageModel) {
            Picasso
                .get()
                .load(url + image.filePath)
                .error(R.drawable.ic_image_error)
                .into(img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPhotosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val img = images[position]
        holder.bind(img)
    }

    override fun getItemCount(): Int = images.size
}