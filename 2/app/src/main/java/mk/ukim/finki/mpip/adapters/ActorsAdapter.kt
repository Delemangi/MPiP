package mk.ukim.finki.mpip.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mk.ukim.finki.mpip.R

class ActorsAdapter(
    private val actors: List<String>
) : RecyclerView.Adapter<ActorsAdapter.ActorsViewHolder>() {

    class ActorsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var movieActor: TextView = view.findViewById(R.id.movie_actor)

        fun bind(actor: String) {
            movieActor.text = actor
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ActorsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.actors_layout, parent, false)

        return ActorsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActorsViewHolder, position: Int) {
        holder.bind(actors[position])
    }

    override fun getItemCount(): Int {
        return actors.size
    }
}