package mk.ukim.finki.mpip.repository

import mk.ukim.finki.mpip.data.FakeApi
import mk.ukim.finki.mpip.model.Movie

class MovieRepository(
    private val movieDbApi: FakeApi
) {
    fun listMovies(): List<Movie> {
        return movieDbApi.getAllMovies()
    }

    fun getMovie(movieId: String): Movie {
        val movies = movieDbApi.getAllMovies()
        val movie = movies.find { it.id.toString() == movieId }
        return movie!!
    }

    fun addMovie(
        title: String,
        description: String,
        director: String,
        actors: List<String>
    ): List<Movie> {
        val movie = Movie(
            movieDbApi.getAllMovies().size + 1,
            title,
            description,
            director,
            actors
        )

        movieDbApi.addMovie(movie)

        return movieDbApi.getAllMovies()
    }
}