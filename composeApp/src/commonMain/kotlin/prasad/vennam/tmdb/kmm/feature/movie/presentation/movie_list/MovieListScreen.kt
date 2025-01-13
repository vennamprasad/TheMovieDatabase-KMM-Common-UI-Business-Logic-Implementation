package prasad.vennam.tmdb.kmm.feature.movie.presentation.movie_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import prasad.vennam.tmdb.kmm.core.presentation.DarkBlue
import prasad.vennam.tmdb.kmm.core.presentation.DesertWhite
import prasad.vennam.tmdb.kmm.core.presentation.SandYellow
import prasad.vennam.tmdb.kmm.feature.movie.domain.Movie
import prasad.vennam.tmdb.kmm.feature.movie.presentation.movie_list.MovieListAction.OnMovieClick
import prasad.vennam.tmdb.kmm.feature.movie.presentation.movie_list.components.MovieList
import prasad.vennam.tmdb.kmm.feature.movie.presentation.movie_list.components.MovieSearchBar
import tmdb.composeapp.generated.resources.Res
import tmdb.composeapp.generated.resources.favorites
import tmdb.composeapp.generated.resources.no_favorites
import tmdb.composeapp.generated.resources.no_search_results
import tmdb.composeapp.generated.resources.search_results

@Composable
fun MovieListScreenRoot(
    viewModel: MovieListViewmodel = koinViewModel(),
    onActionClick: (Movie) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    MovieListScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is OnMovieClick -> {
                    onActionClick(action.movie)
                }

                else -> Unit
            }
            viewModel.dispatch(action)
        },
    )
}

@Composable
fun MovieListScreen(
    state: MovieListState, onAction: (MovieListAction) -> Unit, modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    val pagerState = rememberPagerState { 2 }
    val searchResultsListState = rememberLazyListState()
    val favoriteListState = rememberLazyListState()

    LaunchedEffect(state.searchResultMovies) {
        searchResultsListState.animateScrollToItem(0)
    }

    LaunchedEffect(state.selectedTabIndex) {
        pagerState.animateScrollToPage(state.selectedTabIndex)
    }

    LaunchedEffect(pagerState.currentPage) {
        onAction(MovieListAction.OnTabSelected(pagerState.currentPage))
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBlue)
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MovieSearchBar(
            searchQuery = state.searchQuery,
            onSearchQueryChange = {
                onAction(MovieListAction.OnSearchQueryChange(it))
            },
            onImeSearch = {
                keyboardController?.hide()
            },
            modifier = Modifier
                .widthIn(max = 400.dp)
                .fillMaxWidth()
                .padding(16.dp)
        )
        Surface(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            color = DesertWhite,
            shape = RoundedCornerShape(
                topStart = 32.dp,
                topEnd = 32.dp
            )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TabRow(
                    selectedTabIndex = state.selectedTabIndex,
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .widthIn(max = 700.dp)
                        .fillMaxWidth(),
                    containerColor = DesertWhite,
                    indicator = { tabPositions ->
                        TabRowDefaults.SecondaryIndicator(
                            color = SandYellow,
                            modifier = Modifier
                                .tabIndicatorOffset(tabPositions[state.selectedTabIndex])
                        )
                    }
                ) {
                    Tab(
                        selected = state.selectedTabIndex == 0,
                        onClick = {
                            onAction(MovieListAction.OnTabSelected(0))
                        },
                        modifier = Modifier.weight(1f).padding(4.dp),
                        selectedContentColor = SandYellow,
                        unselectedContentColor = Color.Black.copy(alpha = 0.5f)
                    ) {
                        Text(
                            text = stringResource(Res.string.search_results),
                            modifier = Modifier
                                .padding(vertical = 12.dp)
                        )
                    }
                    Tab(
                        selected = state.selectedTabIndex == 1,
                        onClick = {
                            onAction(MovieListAction.OnTabSelected(1))
                        },
                        modifier = Modifier.weight(1f).padding(4.dp),
                        selectedContentColor = SandYellow,
                        unselectedContentColor = Color.Black.copy(alpha = 0.5f)
                    ) {
                        Text(
                            text = stringResource(Res.string.favorites),
                            modifier = Modifier
                                .padding(vertical = 12.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) { pageIndex ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        when (pageIndex) {
                            0 -> {
                                if (state.isLoading) {
                                    CircularProgressIndicator()
                                } else {
                                    when {
                                        state.error != null -> {
                                            Text(
                                                modifier = modifier.padding(16.dp),
                                                text = state.error.asString(),
                                                textAlign = TextAlign.Center,
                                                style = MaterialTheme.typography.headlineSmall,
                                                color = MaterialTheme.colorScheme.error
                                            )
                                        }

                                        state.searchResultMovies.isEmpty() -> {
                                            Text(
                                                modifier = modifier.padding(16.dp),
                                                text = stringResource(Res.string.no_search_results),
                                                textAlign = TextAlign.Center,
                                                style = MaterialTheme.typography.headlineSmall,
                                                color = MaterialTheme.colorScheme.error
                                            )
                                        }

                                        else -> {
                                            MovieList(
                                                movies = state.searchResultMovies,
                                                onMovieClick = {
                                                    onAction(OnMovieClick(it))
                                                },
                                                modifier = Modifier.fillMaxSize(),
                                                scrollState = searchResultsListState
                                            )
                                        }
                                    }
                                }
                            }

                            1 -> {
                                when {
                                    state.favoriteMovies.isEmpty() -> {
                                        Text(
                                            modifier = modifier.padding(16.dp),
                                            text = stringResource(Res.string.no_favorites),
                                            textAlign = TextAlign.Center,
                                            style = MaterialTheme.typography.headlineSmall,
                                        )
                                    }

                                    state.error != null ->
                                        Text(
                                            modifier = modifier.padding(16.dp),
                                            text = state.error.asString(),
                                            textAlign = TextAlign.Center,
                                            style = MaterialTheme.typography.headlineSmall,
                                            color = MaterialTheme.colorScheme.error
                                        )

                                    else -> {
                                        MovieList(
                                            movies = state.favoriteMovies,
                                            onMovieClick = {
                                                onAction(OnMovieClick(it))
                                            },
                                            modifier = Modifier.fillMaxSize(),
                                            scrollState = favoriteListState
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}