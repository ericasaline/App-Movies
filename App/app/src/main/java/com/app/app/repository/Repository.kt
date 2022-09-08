package com.app.app.repository

import com.app.app.model.CreditsModel
import com.app.app.model.FinalMovieModel
import com.app.app.model.MovieDetailsModel
import com.app.app.response.ImageResponse

sealed class MovieStatus {
    data class Success(val movies: List<FinalMovieModel>): MovieStatus()
    object Error: MovieStatus()
}

sealed class MovieDetailsStatus<out T> {
    data class Success<T>(val data: T): MovieDetailsStatus<T>()
    class Error<T>: MovieDetailsStatus<T>()
}

interface MovieRepository {
    suspend fun nowPlaying(): MovieStatus
    suspend fun upComing(): MovieStatus
    suspend fun movieDetails(movieId: Int): MovieDetailsStatus<MovieDetailsModel>
    suspend fun movieImages(movieId: Int): MovieDetailsStatus<ImageResponse>
    suspend fun movieCredits(movieId: Int): MovieDetailsStatus<List<CreditsModel>>
}