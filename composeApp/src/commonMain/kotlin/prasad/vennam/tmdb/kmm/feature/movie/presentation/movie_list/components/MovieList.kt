package prasad.vennam.tmdb.kmm.feature.movie.presentation.movie_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import prasad.vennam.tmdb.kmm.feature.movie.domain.Movie

@Composable
fun MovieList(
    movies: List<Movie>,
    onMovieClick: (Movie) -> Unit,
    modifier: Modifier,
    scrollState: LazyListState = rememberLazyListState(),
) {
    LazyColumn(
        state = scrollState,
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(movies, key = {
            it.id
        }) { movie ->
            MovieListItem(
                movie = movie,
                modifier = Modifier.fillParentMaxWidth().padding(horizontal = 16.dp),
                onClick = { onMovieClick(movie) },
            )
        }
    }
}