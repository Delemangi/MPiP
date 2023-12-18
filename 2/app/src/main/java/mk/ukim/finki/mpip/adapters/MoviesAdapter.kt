package mk.ukim.finki.mpip.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mk.ukim.finki.mpip.R
import mk.ukim.finki.mpip.model.Movie

class MoviesAdapter(
    private val movies: ArrayList<Movie> = ArrayList(),
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    class MoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var movieId: TextView = view.findViewById(R.id.movie_id)
        private var movieTitle: TextView = view.findViewById(R.id.movie_title)
        private var movieDirector: TextView = view.findViewById(R.id.movie_director)

        fun bind(
            movie: Movie,
            onClickListener: OnClickListener
        ) {
            movieId.text = movie.id.toString()
            movieTitle.text = movie.title
            movieDirector.text = movie.director

            itemView.setOnClickListener {
                onClickListener.onClickItem(movieId.text.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movies_layout, parent, false)

        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(movies[position], onClickListener)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateMovies(movies: List<Movie>) {
        this.movies.clear()
        this.movies.addAll(movies)

        notifyDataSetChanged()
    }

    fun interface OnClickListener {
        fun onClickItem(movieId: String)
    }
}