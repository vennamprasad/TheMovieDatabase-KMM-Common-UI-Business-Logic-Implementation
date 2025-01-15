package prasad.vennam.tmdb.kmm.movie.presentation.movie_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun MovieDetailsScreenRoot(viewmodel: MovieDetailsViewmodel, onBackClick: () -> Unit) {
    val state by viewmodel.state.collectAsStateWithLifecycle()

    MovieDetailScreen(state = state, onAction = { action ->
        when (action) {
            is MovieDetailsAction.OnBackClick -> onBackClick()
            else -> {
                viewmodel.dispatch(action)
            }
        }
    })
}


@Composable
private fun MovieDetailScreen(
    state: MovieDetailState, onAction: (MovieDetailsAction) -> Unit
) {
    val scrollState = rememberScrollState()
    BlurredImageBackground(
        imageUrl = state.movieDetails?.posterPath?.let { "https://image.tmdb.org/t/p/w500$it" },
        isFavorite = state.isFavorite,
        onFavoriteClick = {
            onAction(MovieDetailsAction.OnFavoriteClick(state.movieId!!))
        },
        onBackClick = {
            onAction(MovieDetailsAction.OnBackClick)
        },
        modifier = Modifier.fillMaxSize(),
    ) {
        state.movieDetails?.let { movieDetails ->
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp).verticalScroll(scrollState)
            ) {
                Text(
                    text = movieDetails.title,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Details
                Text(
                    text = "Overview: ${movieDetails.overview}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Vote Average: ${movieDetails.voteAverage} ⭐",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Release Date: ${movieDetails.releaseDate}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Genres: ${movieDetails.genres.joinToString { it.name }}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Runtime: ${movieDetails.runtime} minutes",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Revenue: $${movieDetails.revenue}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Budget: $${movieDetails.budget}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = "Production Companies: ${movieDetails.productionCompanies.joinToString { it.name }}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = "Production Countries: ${movieDetails.productionCountries.joinToString { it.name }}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = "Spoken Languages: ${movieDetails.spokenLanguages.joinToString { it.name }}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = "Status: ${movieDetails.status}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = "Tagline: ${movieDetails.tagline}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = "Vote Count: ${movieDetails.voteCount}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = "Popularity: ${movieDetails.popularity}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = "Video: ${movieDetails.hasVideo}",
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
                                movieDetails.id
                            )
                        )
                    }) {
                        Text("Favorite")
                    }
                    Button(onClick = {
                        onAction(
                            MovieDetailsAction.OnShareClick(
                                movieDetails.id
                            )
                        )
                    }) {
                        Text("Share")
                    }
                    Button(onClick = {
                        onAction(
                            MovieDetailsAction.OnTrailerClick(
                                movieDetails.id
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
                                movieDetails.id
                            )
                        )
                    }) {
                        Text("Reviews")
                    }
                    Button(onClick = {
                        onAction(
                            MovieDetailsAction.OnSimilarMovieClick(
                                movieDetails.id
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

