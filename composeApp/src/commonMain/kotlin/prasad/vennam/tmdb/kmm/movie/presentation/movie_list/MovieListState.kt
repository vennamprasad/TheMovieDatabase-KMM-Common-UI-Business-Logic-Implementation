package prasad.vennam.tmdb.kmm.movie.presentation.movie_list

import prasad.vennam.tmdb.kmm.core.presentation.UiText
import prasad.vennam.tmdb.kmm.movie.domain.Movie
import prasad.vennam.tmdb.kmm.movie.domain.MovieDetails

data class MovieListState(
    val searchQuery: String = "",
    val searchResultMovies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val favoriteMovies: List<Movie> = emptyList(),
    val selectedTabIndex: Int = 0,
    val error: UiText? = null
)


