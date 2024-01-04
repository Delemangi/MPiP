package mk.ukim.finki.mpip.domain.retrofit

import mk.ukim.finki.mpip.domain.model.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDbApi {
    @GET("/")
    suspend fun search(@Query("t") t: String): Response<Movie>
}