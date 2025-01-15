package prasad.vennam.tmdb.kmm.movie.presentation.movie_details

import prasad.vennam.tmdb.kmm.core.presentation.UiText
import prasad.vennam.tmdb.kmm.movie.domain.MovieDetails

data class MovieDetailState(
    val movieId: Int? = null,
    val movieDetails: MovieDetails? = null,
    val isFavorite: Boolean = false,
    val isLoading: Boolean = false,
    val error: UiText? = null
)