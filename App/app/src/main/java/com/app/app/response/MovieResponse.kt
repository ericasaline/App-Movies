package com.app.app.response

import com.app.app.model.MovieModel

data class MovieResponse(
    val results: List<MovieModel>
)