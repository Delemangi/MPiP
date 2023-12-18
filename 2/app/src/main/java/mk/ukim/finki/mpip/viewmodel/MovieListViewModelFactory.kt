package mk.ukim.finki.mpip.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mk.ukim.finki.mpip.data.FakeApiProvider
import mk.ukim.finki.mpip.repository.MovieRepository

class MovieListViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java)
            .newInstance(
                MovieRepository(FakeApiProvider.getFakeApi())
            )
    }
}