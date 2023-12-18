package mk.ukim.finki.mpip.data

import mk.ukim.finki.mpip.model.Movie

class FakeApi {
    private val movies: MutableList<Movie> = ArrayList()

    fun getAllMovies(): List<Movie> {
        return movies
    }

    fun addMovie(movie: Movie) {
        movies.add(movie)
    }
}