package mk.ukim.finki.mpip.model

data class Movie(
    val id: Int,
    val title: String,
    val description: String,
    val director: String,
    val actors: List<String>
)