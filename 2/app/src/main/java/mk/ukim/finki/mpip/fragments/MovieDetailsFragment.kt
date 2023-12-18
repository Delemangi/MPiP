package mk.ukim.finki.mpip.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import mk.ukim.finki.mpip.R
import mk.ukim.finki.mpip.adapters.ActorsAdapter
import mk.ukim.finki.mpip.viewmodel.MovieDetailsViewModel
import mk.ukim.finki.mpip.databinding.FragmentMovieDetailsBinding

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {
    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!
    private val movieDetailsViewModel: MovieDetailsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMovieDetailsBinding.bind(view)

        movieDetailsViewModel.getDetailsForMovieLiveData().observe(viewLifecycleOwner) {
            binding.movieIdLabel.text = it.id.toString()
            binding.movieTitleLabel.text = it.title
            binding.movieDescriptionLabel.text = it.description
            binding.movieDirectorLabel.text = it.director
            binding.movieActorsLabel.adapter = ActorsAdapter(it.actors)
        }
    }
}