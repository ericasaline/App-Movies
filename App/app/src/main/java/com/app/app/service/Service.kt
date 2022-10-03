package com.app.app.service

import com.app.app.model.MovieDetailsModel
import com.app.app.response.CreditResponse
import com.app.app.response.GenreResponse
import com.app.app.response.ImageResponse
import com.app.app.response.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

const val api_key = "api_key=487ebc835337ecb79e8f5cf820682541"

interface Service {
    @GET("movie/now_playing?$api_key")
    suspend fun nowPlaying(): Response<MovieResponse>

    @GET("movie/upcoming?$api_key")
    suspend fun upComing(): Response<MovieResponse>

    @GET("genre/movie/list?$api_key")
    suspend fun genres(): Response<GenreResponse>

    @GET("movie/{movie_id}?$api_key")
    suspend fun movieDetails(@Path("movie_id") movieId: Int): Response<MovieDetailsModel>

    @GET("movie/{movie_id}/images?$api_key")
    suspend fun movieImages(@Path("movie_id") movieId: Int): Response<ImageResponse>

    @GET("movie/{movie_id}/credits?$api_key")
    suspend fun movieCredits(@Path("movie_id") movieId: Int): Response<CreditResponse>
}