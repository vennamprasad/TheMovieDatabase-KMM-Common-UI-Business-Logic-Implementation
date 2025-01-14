package prasad.vennam.tmdb.kmm.movie.presentation.movie_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import prasad.vennam.tmdb.kmm.core.domain.onError
import prasad.vennam.tmdb.kmm.core.domain.onSuccess
import prasad.vennam.tmdb.kmm.core.presentation.toUiText
import prasad.vennam.tmdb.kmm.movie.domain.Movie
import prasad.vennam.tmdb.kmm.movie.domain.MovieRepository

class MovieListViewmodel(
    private val moviesRepository: MovieRepository,
) : ViewModel() {
    private var cachedMovies: List<Movie> = emptyList()
    private var searchJob: Job? = null
    private val _state = MutableStateFlow(MovieListState())
    val state = _state.onStart {
        if (cachedMovies.isEmpty()) {
            observeSearchQuery()
        }
    }.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000L), _state.value
    )

    fun dispatch(action: MovieListAction) {
        when (action) {
            is MovieListAction.OnSearchQueryChange -> {
                _state.update {
                    it.copy(searchQuery = action.query)
                }
            }

            is MovieListAction.OnTabSelected -> {
                _state.update {
                    it.copy(selectedTabIndex = action.index)
                }
            }

            is MovieListAction.OnMovieClick -> {
                // Handle movie click
            }

            is MovieListAction.OnFavoriteClick -> {
                // Handle favorite click
            }
        }
    }

    @OptIn(FlowPreview::class)
    private fun observeSearchQuery() {
        state.map { it.searchQuery }.distinctUntilChanged().debounce(500L).onEach { query ->
            when {
                query.isBlank() -> {
                    _state.update {
                        it.copy(
                            error = null, searchResultMovies = cachedMovies
                        )
                    }
                }

                query.length >= 2 -> {
                    searchJob?.cancel()
                    searchJob = searchMovies(query)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun searchMovies(query: String) = viewModelScope.launch {
        _state.update {
            it.copy(isLoading = true)
        }

        moviesRepository.searchMovies(query, "en-US", true, 1, "US").onSuccess { movies ->
            cachedMovies = movies
            _state.update {
                it.copy(
                    isLoading = false, error = null, searchResultMovies = movies
                )
            }
        }.onError { error ->
            _state.update {
                it.copy(
                    isLoading = false, error = error.toUiText(), searchResultMovies = emptyList()
                )
            }
        }
    }
}