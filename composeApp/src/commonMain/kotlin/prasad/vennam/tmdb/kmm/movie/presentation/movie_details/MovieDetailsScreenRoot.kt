package prasad.vennam.tmdb.kmm.movie.presentation.movie_details

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import org.jetbrains.compose.resources.painterResource
import tmdb.composeapp.generated.resources.Res
import tmdb.composeapp.generated.resources.baseline_error_24

@Composable
fun MovieDetailsScreenRoot(viewmodel: MovieDetailsViewmodel, id: Int, onBack: () -> Unit) {
    val state by viewmodel.state.collectAsStateWithLifecycle()

    LaunchedEffect(id) {
        viewmodel.dispatch(MovieDetailsAction.LoadMovieDetails(movieId = id))
    }
    // Pass the state and actions to the screen
    MovieDetailsScreen(state = state, onAction = { action ->
        when (action) {
            is MovieDetailsAction.OnBackClick -> onBack()
            else -> viewmodel.dispatch(action)
        }
    })
}


@Composable
fun MovieDetailsScreen(
    state: MovieDetailState,
    onAction: (MovieDetailsAction) -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            CollapsingTopBar(
                imageUrl = "https://image.tmdb.org/t/p/w500${state.movieDetails?.posterPath}",
                title = state.movieDetails?.title ?: "",
                onBackClick = { onAction(MovieDetailsAction.OnBackClick) },
                height = 300.dp
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                Snackbar(
                    snackbarData = data,
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                when {
                    state.isLoading -> {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxSize()
                                .wrapContentSize(Alignment.Center)
                        )
                    }

                    state.error != null -> {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .wrapContentSize(Alignment.Center),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = state.error.asString(),
                                color = MaterialTheme.colorScheme.error
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(onClick = { onAction(MovieDetailsAction.OnBackClick) }) {
                                Text("Retry")
                            }
                        }
                    }

                    else -> {
                        state.movieDetails?.let { movie ->
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp)
                                    .verticalScroll(scrollState)
                            ) {
                                Text(
                                    text = movie.title,
                                    style = MaterialTheme.typography.headlineSmall,
                                    modifier = Modifier.padding(bottom = 8.dp)
                                )

                                // Details
                                Text(
                                    text = "Overview: ${movie.overview}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.padding(bottom = 8.dp)
                                )
                                Text(
                                    text = "Rating: ${movie.voteAverage} ⭐",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Text(
                                    text = "Release Date: ${movie.releaseDate}",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Text(
                                    text = "Genres: ${movie.genres.joinToString { it.name }}",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Text(
                                    text = "Runtime: ${movie.runtime} minutes",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Text(
                                    text = "Revenue: $${movie.revenue}",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Text(
                                    text = "Budget: $${movie.budget}",
                                    style = MaterialTheme.typography.bodyMedium
                                )

                                Text(
                                    text = "Production Companies: ${movie.productionCompanies.joinToString { it.name }}",
                                    style = MaterialTheme.typography.bodyMedium
                                )

                                Text(
                                    text = "Production Countries: ${movie.productionCountries.joinToString { it.name }}",
                                    style = MaterialTheme.typography.bodyMedium
                                )

                                Text(
                                    text = "Spoken Languages: ${movie.spokenLanguages.joinToString { it.name }}",
                                    style = MaterialTheme.typography.bodyMedium
                                )

                                Text(
                                    text = "Status: ${movie.status}",
                                    style = MaterialTheme.typography.bodyMedium
                                )

                                Text(
                                    text = "Tagline: ${movie.tagline}",
                                    style = MaterialTheme.typography.bodyMedium
                                )

                                Text(
                                    text = "Vote Count: ${movie.voteCount}",
                                    style = MaterialTheme.typography.bodyMedium
                                )

                                Text(
                                    text = "Popularity: ${movie.popularity}",
                                    style = MaterialTheme.typography.bodyMedium
                                )

                                Text(
                                    text = "Video: ${movie.hasVideo}",
                                    style = MaterialTheme.typography.bodyMedium
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                // Buttons
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    Button(onClick = {
                                        onAction(
                                            MovieDetailsAction.OnFavoriteClick(
                                                movie.id
                                            )
                                        )
                                    }) {
                                        Text("Favorite")
                                    }
                                    Button(onClick = {
                                        onAction(
                                            MovieDetailsAction.OnShareClick(
                                                movie.id
                                            )
                                        )
                                    }) {
                                        Text("Share")
                                    }
                                    Button(onClick = {
                                        onAction(
                                            MovieDetailsAction.OnTrailerClick(
                                                movie.id
                                            )
                                        )
                                    }) {
                                        Text("Watch Trailer")
                                    }
                                }

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    Button(onClick = {
                                        onAction(
                                            MovieDetailsAction.OnReviewClick(
                                                movie.id
                                            )
                                        )
                                    }) {
                                        Text("Reviews")
                                    }
                                    Button(onClick = {
                                        onAction(
                                            MovieDetailsAction.OnSimilarMovieClick(
                                                movie.id
                                            )
                                        )
                                    }) {
                                        Text("Similar Movies")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
private fun CollapsingTopBar(
    imageUrl: String, title: String, onBackClick: () -> Unit, height: Dp
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.TopStart
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier
                .padding(16.dp)
                .size(24.dp)
                .clickable { onBackClick() },
            tint = MaterialTheme.colorScheme.onPrimary
        )
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            var imageLoadResult by remember {
                mutableStateOf<Result<Painter>?>(null)
            }
            val painter = rememberAsyncImagePainter(
                model = "https://image.tmdb.org/t/p/w500${imageUrl}",
                onSuccess = {
                    imageLoadResult =
                        if (it.painter.intrinsicSize.width > 1 && it.painter.intrinsicSize.height > 1) {
                            Result.success(it.painter)
                        } else {
                            Result.failure(Exception("Invalid image size"))
                        }
                },
                onError = {
                    it.result.throwable.printStackTrace()
                    imageLoadResult = Result.failure(it.result.throwable)
                }
            )

            val painterState by painter.state.collectAsStateWithLifecycle()
            val transition by animateFloatAsState(
                targetValue = if (painterState is AsyncImagePainter.State.Success) {
                    1f
                } else {
                    0f
                },
                animationSpec = tween(durationMillis = 800)
            )

            when (val result = imageLoadResult) {
                null -> CircularProgressIndicator(
                    modifier = Modifier.size(60.dp)
                )

                else -> {
                    Image(
                        painter = if (result.isSuccess) painter else {
                            painterResource(Res.drawable.baseline_error_24)
                        },
                        contentDescription = title,
                        modifier = Modifier
                            .fillMaxSize()
                            .graphicsLayer(alpha = transition)
                    )
                }
            }
        }
    }
}

