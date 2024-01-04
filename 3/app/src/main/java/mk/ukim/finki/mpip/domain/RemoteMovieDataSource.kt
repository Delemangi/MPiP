package mk.ukim.finki.mpip.domain

import mk.ukim.finki.mpip.domain.model.Movie

interface RemoteMovieDataSource {
    suspend fun search(query: String): Movie
}