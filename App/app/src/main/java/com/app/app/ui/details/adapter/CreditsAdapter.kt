package com.app.app.ui.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.app.R
import com.app.app.databinding.ItemCreditsBinding
import com.app.app.model.CreditsModel
import com.squareup.picasso.Picasso

class CreditsAdapter(private val castcrew: List<CreditsModel>)
    : RecyclerView.Adapter<CreditsAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemCreditsBinding)
        : RecyclerView.ViewHolder(binding.root) {

        private val url = "https://image.tmdb.org/t/p/w500"
        private val name = binding.itemCharacter
        private val photo = binding.cardPhoto
        private val job = binding.itemActor

        fun bind(credits: CreditsModel) {
            name.text = credits.name
            job.text = credits.job
            Picasso
                .get()
                .load(url + credits.photo)
                .error(R.drawable.ic_people)
                .into(photo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCreditsBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = castcrew[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = castcrew.size
}