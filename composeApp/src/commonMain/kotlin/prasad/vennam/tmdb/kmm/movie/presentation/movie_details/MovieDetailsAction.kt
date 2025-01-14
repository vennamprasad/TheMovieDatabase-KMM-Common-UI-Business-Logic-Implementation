package prasad.vennam.tmdb.kmm.movie.presentation.movie_details

sealed interface MovieDetailsAction {
    data class LoadMovieDetails(val movieId: Int) : MovieDetailsAction
    data class OnFavoriteClick(val movieId: Int) : MovieDetailsAction
    data object OnBackClick : MovieDetailsAction
    data class OnShareClick(val movieId: Int) : MovieDetailsAction
    data class OnTrailerClick(val movieId: Int) : MovieDetailsAction
    data class OnReviewClick(val movieId: Int) : MovieDetailsAction
    data class OnSimilarMovieClick(val movieId: Int) : MovieDetailsAction
}