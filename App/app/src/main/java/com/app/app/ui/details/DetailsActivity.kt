package com.app.app.ui.details

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.app.app.R
import com.app.app.databinding.ActivityDetailsBinding
import com.app.app.databinding.DialogErrorBinding
import com.app.app.ui.details.adapter.CreditsAdapter
import com.app.app.ui.details.adapter.ImagesAdapter
import com.app.app.ui.home.MainActivity
import com.app.app.ui.viewmodel.ViewModel
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private val viewModel : ViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        back()
        observeDetailsSuccess()
        observeCreditsSuccess()
        observeImages()
        observeDetailsError()
        observeCreditsError()
        movieDetails()
        movieImages()
        movieCredits()
    }

    companion object {
        private const val MOVIE_ID = "MOVIE_ID"
        fun newIntent(context: Context, movieId: Int?): Intent {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(MOVIE_ID, movieId)
            return intent
        }
    }

    private fun back() {
        binding.detailsArrow.setOnClickListener {
            finish()
        }
    }

    private fun observeCreditsSuccess() {
        viewModel.credits.observe(this) {
            binding.detailsCreditsRecycler.adapter = it?.let {
                    it1 -> CreditsAdapter(it1)
            }
        }
    }

    private fun observeImages() {
        viewModel.images.observe(this) {
            binding.detailsPhotosRecycler.adapter = ImagesAdapter(it)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun observeDetailsSuccess() {
        viewModel.movieDetail.observe(this){
            binding.detailsTitle.text = it?.originalTitle
            binding.detailsAverage.text = it?.voteAverage.toString().substring(0,3)
            binding.detailsSynopsisText.text = it?.overview

            if (it != null) {
                binding.detailsGenre.text = it.genres.first().name
            }

            val hr = it?.runtime?.div(60)
            val m = it?.runtime?.rem(60)
            binding.detailsDuration.text = hr.toString() + "hr " + m.toString() + "m"

            Picasso
                .get()
                .load("https://image.tmdb.org/t/p/w500" + it?.backdropPath)
                .error(R.drawable.ic_image_error)
                .into(binding.detailsPoster)
        }
    }

    private fun observeCreditsError() {
        viewModel.movieCreditsError.observe(this) {
            showDialogError()
        }
    }

    private fun observeDetailsError() {
        viewModel.movieDetailsError.observe(this) {
            showDialogError()
        }
    }

    private fun movieDetails() {
        viewModel.movieDetails(intent.getIntExtra(MOVIE_ID, 1))
    }

    private fun movieCredits() {
        viewModel.movieCredits(intent.getIntExtra(MOVIE_ID, 1))
    }

    private fun movieImages() {
        viewModel.movieImages(intent.getIntExtra(MOVIE_ID, 1))
    }

    private fun showDialogError() {
        val dialog: AlertDialog
        val view = DialogErrorBinding.inflate(layoutInflater)
        val build = AlertDialog.Builder(this, R.style.AlertDialog)

        build.setView(view.root)
        dialog = build.create()
        dialog.show()

        view.dialogButtonError.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}