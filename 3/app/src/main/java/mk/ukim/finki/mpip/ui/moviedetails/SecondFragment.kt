package mk.ukim.finki.mpip.ui.moviedetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import mk.ukim.finki.mpip.ui.movielist.MoviesViewModelFactory
import mk.ukim.finki.mpip.R
import mk.ukim.finki.mpip.databinding.FragmentMovieDetailsBinding

class SecondFragment : Fragment(R.layout.fragment_movie_details) {
    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    private val movieDetailsViewModel: MovieDetailsViewModel by activityViewModels {
        MoviesViewModelFactory(
            requireContext()
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMovieDetailsBinding.bind(view)

        movieDetailsViewModel.getDetailsForMovieLiveData().observe(viewLifecycleOwner) {
            val movie = movieDetailsViewModel.getDetailsForMovieLiveData().value

            Glide.with(binding.movieImage)
                .load(movie?.poster)
                .centerCrop().placeholder(R.drawable.ic_movie).into(binding.movieImage)

            binding.movieIdLabel.text = it.imdbID
            binding.movieTitleLabel.text = it.title
            binding.movieGenreLabel.text = it.genre
            binding.movieYearLabel.text = it.year
            binding.movieRuntimeLabel.text = it.runtime
            binding.movieDirectorLabel.text = it.director
            binding.movieActorsLabel.text = it.actors
            binding.moviePlotLabel.text = it.plot
            binding.movieCountryLabel.text = it.country
            binding.movieLanguageLabel.text = it.language
        }
    }
}
