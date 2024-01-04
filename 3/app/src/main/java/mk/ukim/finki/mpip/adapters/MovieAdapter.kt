package mk.ukim.finki.mpip.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mk.ukim.finki.mpip.R
import mk.ukim.finki.mpip.domain.model.Movie

class MovieAdapter(
    private val movies: ArrayList<Movie> = ArrayList(),
    private val onClickListener: OnClickListener
) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    interface OnClickListener {
        fun onClickItem(movieId: String)
    }

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var imageView: ImageView = view.findViewById(R.id.movie_image)
        private var titleText: TextView = view.findViewById(R.id.movie_title)
        private var yearText: TextView = view.findViewById(R.id.movie_year)

        fun bind(
            movie: Movie,
            onClickListener: OnClickListener
        ) {
            Glide.with(imageView)
                .load(movie.poster)
                .centerCrop().placeholder(R.drawable.ic_movie).into(imageView)

            titleText.text = movie.title
            yearText.text = movie.year

            itemView.setOnClickListener {
                onClickListener.onClickItem(movie.imdbID)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_movie, parent, false)

        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position], onClickListener)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateMovies(movies: List<Movie>?) {
        this.movies.clear()

        if (movies != null) {
            this.movies.addAll(movies)
        }

        notifyDataSetChanged()
    }
}
