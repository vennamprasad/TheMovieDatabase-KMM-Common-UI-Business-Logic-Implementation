package prasad.vennam.tmdb.kmm.movie.presentation.movie_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import prasad.vennam.tmdb.kmm.core.domain.onError
import prasad.vennam.tmdb.kmm.core.domain.onSuccess
import prasad.vennam.tmdb.kmm.core.presentation.toUiText
import prasad.vennam.tmdb.kmm.movie.domain.MovieRepository

class MovieDetailsViewmodel(
    private val moviesRepository: MovieRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(MovieDetailState())
    val state = _state.asStateFlow()


    fun dispatch(action: MovieDetailsAction) {
        when (action) {
            is MovieDetailsAction.LoadMovieDetails -> {
                getMovieDetails(action.movieId)
            }

            is MovieDetailsAction.OnFavoriteClick -> {
                // Handle favorite click
            }

            is MovieDetailsAction.OnBackClick -> {
                // Handle back click
            }

            is MovieDetailsAction.OnShareClick -> {
                // Handle share click
            }

            is MovieDetailsAction.OnTrailerClick -> {
                // Handle trailer click
            }

            is MovieDetailsAction.OnReviewClick -> {
                // Handle review click
            }

            is MovieDetailsAction.OnSimilarMovieClick -> {
                // Handle similar movie click
            }
        }
    }

    private fun getMovieDetails(movieId: Int) = viewModelScope.launch {
        _state.update {
            it.copy(isLoading = true)
        }

        moviesRepository.getMovieDetails(movieId).onSuccess { movieDetails ->
            _state.update {
                it.copy(
                    isLoading = false, error = null, movieDetails = movieDetails
                )
            }
        }.onError { error ->
            _state.update {
                it.copy(
                    isLoading = false, error = error.toUiText()
                )
            }
        }
    }
}
