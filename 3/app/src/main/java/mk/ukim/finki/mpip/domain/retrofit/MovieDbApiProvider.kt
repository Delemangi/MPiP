package mk.ukim.finki.mpip.domain.retrofit

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import mk.ukim.finki.mpip.BuildConfig

class MovieDbApiProvider {
    companion object {
        @Volatile
        private var INSTANCE: MovieDbApi? = null

        @JvmStatic
        fun getMovieDbApi(): MovieDbApi {
            return INSTANCE ?: synchronized(this) {
                val instance = createMovieDbApi()

                INSTANCE = instance
                instance
            }
        }

        private fun createMovieDbApi(): MovieDbApi {
            class QueryParamInterceptor : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    var request: Request = chain.request()

                    val htt = request.url.newBuilder()
                        .addQueryParameter("apikey", BuildConfig.MOVIE_DB_API_KEY)
                        .addQueryParameter("plot", "short")
                        .build()

                    request = request.newBuilder().url(htt).build()

                    return chain.proceed(request)
                }
            }

            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val okhttpClient = OkHttpClient.Builder()
                .addInterceptor(QueryParamInterceptor())
                .addInterceptor(httpLoggingInterceptor)
                .build()

            val gson = GsonBuilder()
                .setLenient()
                .create()

            val gsonConverterFactory = GsonConverterFactory.create(gson)

            val retrofit = Retrofit.Builder()
                .baseUrl("https://www.omdbapi.com")
                .client(okhttpClient)
                .addConverterFactory(gsonConverterFactory)
                .build()

            return retrofit.create(MovieDbApi::class.java)
        }
    }
}
