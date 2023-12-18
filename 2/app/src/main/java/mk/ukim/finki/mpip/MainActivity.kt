package mk.ukim.finki.mpip

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.commit
import mk.ukim.finki.mpip.data.FakeApiProvider
import mk.ukim.finki.mpip.fragments.AddNewMovieDialogFragment
import mk.ukim.finki.mpip.fragments.MovieListFragment
import mk.ukim.finki.mpip.repository.MovieRepository

class MainActivity : AppCompatActivity(), AddNewMovieDialogFragment.AddMovieDialogListener {
    private lateinit var repository: MovieRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add(R.id.fragment_container_view, MovieListFragment())
                setReorderingAllowed(true)
            }
        }

        this.repository = MovieRepository(FakeApiProvider.getFakeApi())
    }

    override fun onDialogPositiveClick(
        title: String,
        description: String,
        director: String,
        actors: ArrayList<String>
    ) {
        repository.addMovie(title, description, director, actors)

        supportFragmentManager.commit {
            replace(R.id.fragment_container_view, MovieListFragment())
            setReorderingAllowed(true)
        }
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        dialog.dismiss()
    }
}