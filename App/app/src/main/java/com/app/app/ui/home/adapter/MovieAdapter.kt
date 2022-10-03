package com.app.app.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.app.R
import com.app.app.databinding.ItemMovieBinding
import com.app.app.model.FinalMovieModel
import com.app.app.ui.details.DetailsActivity
import com.squareup.picasso.Picasso

class MovieAdapter(private val movies: List<FinalMovieModel>)
    : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemMovieBinding)
        : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        private lateinit var movie: FinalMovieModel

        init {
            binding.root.setOnClickListener(this)
        }

        private val url = "https://image.tmdb.org/t/p/w500"
        private val img = binding.mainImg
        private val title = binding.mainTextMovie
        private val genreAverage = binding.mainTextGenreAverage
        private val date = binding.mainTextInfos

        @SuppressLint("SetTextI18n")
        fun bind(movie: FinalMovieModel) {
            this.movie = movie

            Picasso
                .get()
                .load(url + movie.posterPath)
                .error(R.drawable.ic_image_error)
                .into(img)

            title.text = movie.title
            genreAverage.text = movie.genres?.first()?.name + "  •  " + movie.voteAverage.toString()
            date.text = movie.releaseDate
        }

        override fun onClick(v: View?) {
            v?.let {
                val context = it.context
                val intent = DetailsActivity.newIntent(context, movie.id)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movies = movies[position]
        holder.bind(movies)
    }

    override fun getItemCount(): Int = movies.size
}