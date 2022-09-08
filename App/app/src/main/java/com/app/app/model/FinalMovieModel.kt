package com.app.app.model

class FinalMovieModel(movie: MovieModel, allGenres: List<GenreModel>) {

    private val originalLanguage: String?
    private val originalTitle: String?
    private val overview: String?
    private val popularity: Double?
    private val video: Boolean?
    private val voteCount: Double?
    val id: Int?
    val posterPath: String?
    val releaseDate: String?
    val genres: List<GenreModel>?
    val title: String?
    val voteAverage: Double?

    init {
        id = movie.id
        originalLanguage = movie.originalLanguage
        originalTitle = movie.originalTitle
        overview = movie.overview
        popularity = movie.popularity
        posterPath = movie.posterPath
        releaseDate = movie.releaseDate
        title = movie.title
        video = movie.video
        voteAverage = movie.voteAverage
        voteCount = movie.voteCount

        val arrayAux = arrayListOf<GenreModel>()

        movie.genres?.forEach {
            getGenre(it, allGenres).let { genreModel ->
                arrayAux.add(genreModel)
            }
        }
        genres = arrayAux.toList()
    }

    private fun getGenre(genreId: Int?, allGenres: List<GenreModel>): GenreModel {
        return allGenres.first {
            (it.id == genreId)
        }
    }
}