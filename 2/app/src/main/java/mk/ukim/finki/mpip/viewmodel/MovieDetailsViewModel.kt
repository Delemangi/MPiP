package mk.ukim.finki.mpip.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mk.ukim.finki.mpip.model.Movie
import mk.ukim.finki.mpip.repository.MovieRepository

class MovieDetailsViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {
    private val detailsForMovieLiveData = MutableLiveData<Movie>()

    fun getDetailsForMovieLiveData(): LiveData<Movie> = detailsForMovieLiveData

    fun listAllDetails(movieId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val movie = movieRepository.getMovie(movieId)
            detailsForMovieLiveData.postValue(movie)
        }
    }
}