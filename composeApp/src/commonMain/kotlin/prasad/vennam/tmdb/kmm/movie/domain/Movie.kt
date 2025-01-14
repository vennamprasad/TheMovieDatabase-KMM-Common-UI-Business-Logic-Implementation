package prasad.vennam.tmdb.kmm.movie.domain

data class Movie(
    val isAdult: Boolean = false,
    val backdropPath: String? = "",
    val genreIds: List<Int>? = listOf(),
    val id: Int = 0,
    val originalLanguage: String? = "",
    val originalTitle: String? = "",
    val overview: String? = "",
    val popularity: Double = 0.0,
    val posterPath: String? = "",
    val releaseDate: String? = "",
    val title: String? = "",
    val isVideo: Boolean = false,
    val voteAverage: Double = 0.0,
    val voteCount: Int? = 0
)