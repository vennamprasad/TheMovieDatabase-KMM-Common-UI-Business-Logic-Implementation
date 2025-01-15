package prasad.vennam.tmdb.kmm.movie.presentation.movie_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import prasad.vennam.tmdb.kmm.app.Route
import prasad.vennam.tmdb.kmm.core.domain.onError
import prasad.vennam.tmdb.kmm.core.domain.onSuccess
import prasad.vennam.tmdb.kmm.core.presentation.toUiText
import prasad.vennam.tmdb.kmm.movie.domain.MovieRepository

class MovieDetailsViewmodel(
    private val moviesRepository: MovieRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val movieId = savedStateHandle.toRoute<Route.MovieDetail>().id
    private val _state = MutableStateFlow(MovieDetailState())
    val state = _state
        .onStart {
            getMovieDetails(movieId)
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )


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

        moviesRepository.getMovieDetails(movieId)
            .onSuccess { movieDetails ->
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
