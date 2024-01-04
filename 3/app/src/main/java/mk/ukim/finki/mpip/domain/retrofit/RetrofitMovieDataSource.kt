package mk.ukim.finki.mpip.domain.retrofit

import mk.ukim.finki.mpip.domain.RemoteMovieDataSource
import mk.ukim.finki.mpip.domain.model.Movie

class RetrofitMovieDataSource(private val movieDbApi: MovieDbApi) : RemoteMovieDataSource {
    override suspend fun search(query: String): Movie {
        val movieResponse = movieDbApi.search(query)
        val responseBody = movieResponse.body()

        if (movieResponse.isSuccessful && responseBody != null) {
            return responseBody
        }

        throw Exception(movieResponse.message())
    }
}
