package com.app.app.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.app.model.CreditsModel
import com.app.app.model.FinalMovieModel
import com.app.app.model.ImageModel
import com.app.app.model.MovieDetailsModel
import com.app.app.repository.MovieDetailsStatus
import com.app.app.repository.MovieRepository
import com.app.app.repository.MovieStatus
import kotlinx.coroutines.launch

class ViewModel(private val repository: MovieRepository): ViewModel() {

    private val _movieImagesError = MutableLiveData<Boolean>()

    private val _movies = MutableLiveData<List<FinalMovieModel>>()
    val movies: LiveData<List<FinalMovieModel>> get() = _movies

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> get() = _error

    private val _movieDetail = MutableLiveData<MovieDetailsModel?>()
    val movieDetail: LiveData<MovieDetailsModel?> get() = _movieDetail

    private val _movieDetailsError = MutableLiveData<Boolean>()
    val movieDetailsError: LiveData<Boolean> get() = _movieDetailsError

    private val _images = MutableLiveData<List<ImageModel>>()
    val images: LiveData<List<ImageModel>> get() = _images

    private val _credits = MutableLiveData<List<CreditsModel>?>()
    val credits: LiveData<List<CreditsModel>?> get() = _credits

    private val _movieCreditsError = MutableLiveData<Boolean>()
    val movieCreditsError: LiveData<Boolean> get() = _movieCreditsError

    fun nowPlaying() {
        viewModelScope.launch {
            when (val result = repository.nowPlaying()) {
                is MovieStatus.Success -> _movies.postValue(result.movies)
                else -> _error.postValue(true)
            }
        }
    }

    fun upComing() {
        viewModelScope.launch {
            when (val result = repository.upComing()) {
                is MovieStatus.Success -> _movies.postValue(result.movies)
                else -> _error.postValue(true)
            }
        }
    }

    fun movieDetails(movieId: Int) {
        viewModelScope.launch {
            when (val details = repository.movieDetails(movieId)) {
                is MovieDetailsStatus.Success -> _movieDetail.postValue(details.data)
                else -> _movieDetailsError.postValue(true)
            }
        }
    }

    fun movieImages(movieId: Int) {
        viewModelScope.launch {
            when (val images = repository.movieImages(movieId)) {
                is MovieDetailsStatus.Success -> _images.postValue(images.data.images)
                else -> _movieImagesError.postValue(true)
            }
        }
    }

    fun movieCredits(movieId: Int) {
        viewModelScope.launch {
            when (val credits = repository.movieCredits(movieId)) {
                is MovieDetailsStatus.Success -> _credits.postValue(credits.data)
                else -> _movieCreditsError.postValue(true)
            }
        }
    }
}