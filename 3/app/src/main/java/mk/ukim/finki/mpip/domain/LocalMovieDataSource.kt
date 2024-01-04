package mk.ukim.finki.mpip.domain

import mk.ukim.finki.mpip.domain.model.Movie

interface LocalMovieDataSource {
    suspend fun insert(movie: Movie)
    suspend fun getAll(): List<Movie>
    suspend fun getById(id: String): Movie
}
