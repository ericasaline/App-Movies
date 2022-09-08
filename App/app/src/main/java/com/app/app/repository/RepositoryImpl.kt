package com.app.app.repository

import com.app.app.model.CreditsModel
import com.app.app.model.FinalMovieModel
import com.app.app.model.MovieDetailsModel
import com.app.app.response.ImageResponse
import com.app.app.response.MovieResponse
import com.app.app.service.Service
import retrofit2.Response

class RepositoryImpl(private val service: Service): MovieRepository {

    private suspend fun finalMovieModel(movieResponse: MovieResponse): MovieStatus {
        val genres = service.genres()
        if (genres.isSuccessful) {
            val movieArray = arrayListOf<FinalMovieModel>()
            genres.body()?.let { genreResponse ->
                movieResponse.results.forEach {
                    movieArray.add(FinalMovieModel(it, genreResponse.genres))
                }
                return MovieStatus.Success(movieArray.toList())
            }
        }
        return MovieStatus.Error
    }

    override suspend fun nowPlaying(): MovieStatus {
        if (service.nowPlaying().isSuccessful) {
            service.nowPlaying().body()?.let {
                return finalMovieModel(it)
            }
        }
        return MovieStatus.Error
    }

    override suspend fun upComing(): MovieStatus {
        if (service.upComing().isSuccessful) {
            service.upComing().body()?.let {
                return finalMovieModel(it)
            }
        }
        return MovieStatus.Error
    }

    private fun <T> processResponse(response: Response<T>): MovieDetailsStatus<T> {
        return when (response.code()){
            in 200..299 -> {
                response.body()?.let{
                    MovieDetailsStatus.Success(it)
                } ?: MovieDetailsStatus.Error()
            }
            401 -> MovieDetailsStatus.Error()
            404 -> MovieDetailsStatus.Error()
            else -> MovieDetailsStatus.Error()
        }
    }

    override suspend fun movieDetails(movieId: Int): MovieDetailsStatus<MovieDetailsModel> {
        return processResponse(service.movieDetails(movieId))
    }

    override suspend fun movieImages(movieId: Int): MovieDetailsStatus<ImageResponse> {
        return processResponse(service.movieImages(movieId))
    }

    override suspend fun movieCredits(movieId: Int): MovieDetailsStatus<List<CreditsModel>> {
        val credits = arrayListOf<CreditsModel>()
        val castCrewResponse = service.movieCredits(movieId)
        castCrewResponse.body()?.let {
            it.cast.forEach { castModel ->
                val cast = CreditsModel(castModel, null)
                credits.add(cast)
            }
            it.crew.forEach { crewModel ->
                val crew = CreditsModel(null, crewModel)
                credits.add(crew)
            }
            return MovieDetailsStatus.Success(credits.toList())
        }
        return MovieDetailsStatus.Error()
    }
}