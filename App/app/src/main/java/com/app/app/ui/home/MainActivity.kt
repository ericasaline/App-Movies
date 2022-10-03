package com.app.app.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.commit
import androidx.recyclerview.widget.GridLayoutManager
import com.app.app.R
import com.app.app.databinding.ActivityMainBinding
import com.app.app.ui.error.ErrorFragment
import com.app.app.ui.error.ErrorFragment.Companion.TAG
import com.app.app.ui.home.adapter.MovieAdapter
import com.app.app.ui.viewmodel.ViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: ViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observerSuccess()
        observerError()
        click()
    }

    private fun observerSuccess() {
        viewModel.movies.observe(this) {
            binding.mainRecyclerview.adapter = MovieAdapter(it)
            binding.mainRecyclerview.layoutManager = GridLayoutManager(this, 2)
            binding.mainRecyclerview.visibility = View.VISIBLE
        }
    }

    private fun observerError() {
        viewModel.error.observe(this) {
            binding.mainRecyclerview.visibility = View.GONE
            binding.mainContainer.visibility = View.VISIBLE
            supportFragmentManager.commit {
                replace(R.id.main_container, ErrorFragment.newInstance(), TAG)
            }
        }
    }

    @SuppressLint("ResourceType")
    private fun click() {
        viewModel.nowPlaying()

        binding.mainBtnNp.setOnClickListener {

            binding.mainBtnNp.setBackgroundResource(R.drawable.button_background)
            binding.mainBtnUp.setBackgroundColor(ContextCompat
                .getColor(applicationContext, R.color.light_blue))

            viewModel.nowPlaying()
        }

        binding.mainBtnUp.setOnClickListener {

            binding.mainBtnUp.setBackgroundResource(R.drawable.button_background)
            binding.mainBtnNp.setBackgroundColor(ContextCompat
                    .getColor(applicationContext, R.color.light_blue))

            viewModel.upComing()
        }
    }
}