package mk.ukim.finki.mpip.data

import mk.ukim.finki.mpip.model.Movie

class FakeApiProvider {
    companion object {
        @Volatile
        private var INSTANCE: FakeApi? = null

        @JvmStatic
        fun getFakeApi(): FakeApi {
            return INSTANCE ?: synchronized(this) {
                val instance = createFakeApi()
                INSTANCE = instance
                instance
            }
        }

        private fun createFakeApi(): FakeApi {
            val movies = FakeApi()

            movies.addMovie(Movie(1, "M1", "D1", "D1", listOf("A1", "A2")))
            movies.addMovie(Movie(2, "M2", "D2", "D2", listOf("A2", "A3")))
            movies.addMovie(Movie(3, "M3", "D3", "D3", listOf("A3", "A4")))
            movies.addMovie(Movie(4, "M4", "D4", "D4", listOf("A4", "A5")))
            movies.addMovie(Movie(5, "M5", "D5", "D5", listOf("A5", "A6")))
            movies.addMovie(Movie(6, "M6", "D6", "D6", listOf("A6", "A7")))
            movies.addMovie(Movie(7, "M7", "D7", "D7", listOf("A7", "A8")))
            movies.addMovie(Movie(8, "M8", "D8", "D8", listOf("A8", "A9")))
            movies.addMovie(Movie(9, "M9", "D9", "D9", listOf("A9", "A10")))

            return movies
        }
    }
}