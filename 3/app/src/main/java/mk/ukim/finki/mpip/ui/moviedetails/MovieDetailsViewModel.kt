package mk.ukim.finki.mpip.ui.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import mk.ukim.finki.mpip.domain.model.Movie
import mk.ukim.finki.mpip.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    private val detailsForMovieLiveData = MutableLiveData<Movie>()

    fun getDetailsForMovieLiveData(): LiveData<Movie> = detailsForMovieLiveData

    fun listAllDetails(movieId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val movie = movieRepository.listById(movieId)
            detailsForMovieLiveData.postValue(movie)
        }
    }
}