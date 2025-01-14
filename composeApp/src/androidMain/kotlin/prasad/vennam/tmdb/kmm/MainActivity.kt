package prasad.vennam.tmdb.kmm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import prasad.vennam.tmdb.kmm.app.App
import prasad.vennam.tmdb.kmm.movie.domain.Movie
import prasad.vennam.tmdb.kmm.movie.presentation.movie_list.MovieListScreen
import prasad.vennam.tmdb.kmm.movie.presentation.movie_list.MovieListState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}


@Composable
@Preview
fun MovieListScreenPreview() {
    MovieListScreen(
        state = MovieListState(
            searchQuery = "Batman", searchResultMovies = listOf(
                Movie(
                    id = 1, title = "Batman", overview = "The Dark Knight", posterPath = "/path"
                )
            ), isLoading = false, favoriteMovies = emptyList(), selectedTabIndex = 0, error = null
        ), onAction = {

        }, modifier = Modifier.fillMaxSize()
    )
}