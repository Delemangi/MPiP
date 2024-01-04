package mk.ukim.finki.mpip.ui.movielist

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mk.ukim.finki.mpip.domain.repository.MovieRepository
import mk.ukim.finki.mpip.domain.retrofit.MovieDbApiProvider
import mk.ukim.finki.mpip.domain.retrofit.RetrofitMovieDataSource
import mk.ukim.finki.mpip.domain.room.AppDatabase
import mk.ukim.finki.mpip.domain.room.RoomMovieDataSource

class MoviesViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java)
            .newInstance(
                MovieRepository(
                    RetrofitMovieDataSource(MovieDbApiProvider.getMovieDbApi()),
                    RoomMovieDataSource(AppDatabase.getDatabase(context).movieDao())
                )
            )
    }
}
