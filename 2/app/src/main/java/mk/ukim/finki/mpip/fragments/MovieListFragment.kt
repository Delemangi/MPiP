package mk.ukim.finki.mpip.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import mk.ukim.finki.mpip.R
import mk.ukim.finki.mpip.adapters.MoviesAdapter
import mk.ukim.finki.mpip.databinding.FragmentMoviesBinding
import mk.ukim.finki.mpip.viewmodel.MovieDetailsViewModel
import mk.ukim.finki.mpip.viewmodel.MovieListViewModel
import mk.ukim.finki.mpip.viewmodel.MovieListViewModelFactory

class MovieListFragment : Fragment(R.layout.fragment_movies) {
    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!
    private lateinit var movieListViewModel: MovieListViewModel

    private val movieDetailsViewModel: MovieDetailsViewModel by activityViewModels {
        MovieListViewModelFactory(
            requireContext()
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMoviesBinding.bind(view)

        val viewModelFactory = MovieListViewModelFactory(requireContext())
        movieListViewModel =
            ViewModelProvider(this, viewModelFactory)[MovieListViewModel::class.java]

        val clicker = MoviesAdapter.OnClickListener { movieId ->
            movieDetailsViewModel.listAllDetails(movieId)

            parentFragmentManager.commit {
                replace(R.id.fragment_container_view, MovieDetailsFragment())
                setReorderingAllowed(true)
                addToBackStack(null)
            }
        }

        val adapter = MoviesAdapter(ArrayList(), clicker)
        binding.list.adapter = adapter

        movieListViewModel.getMovieLiveData().observe(viewLifecycleOwner) {
            adapter.updateMovies(it)
        }

        movieListViewModel.listAll()

        binding.buttonAdd.setOnClickListener {
            AddNewMovieDialogFragment().show(childFragmentManager, "add-movie-dialog")
        }
    }
}