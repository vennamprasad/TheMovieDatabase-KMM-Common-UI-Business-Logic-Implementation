package prasad.vennam.tmdb.kmm.movie.presentation.movie_list

import prasad.vennam.tmdb.kmm.movie.domain.Movie

sealed interface MovieListAction {
    data class OnSearchQueryChange(val query: String) : MovieListAction
    data class OnTabSelected(val index: Int) : MovieListAction
    data class OnMovieClick(val movie: Movie) : MovieListAction
    data class OnFavoriteClick(val movie: Movie) : MovieListAction
}