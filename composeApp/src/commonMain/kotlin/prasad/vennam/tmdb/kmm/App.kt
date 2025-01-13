package prasad.vennam.tmdb.kmm

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import io.ktor.client.engine.HttpClientEngine
import org.jetbrains.compose.ui.tooling.preview.Preview
import prasad.vennam.tmdb.kmm.core.data.HttpClientFactory
import prasad.vennam.tmdb.kmm.feature.movie.data.network.RemoteDataSourceImpl
import prasad.vennam.tmdb.kmm.feature.movie.data.repository.DefaultRepository
import prasad.vennam.tmdb.kmm.feature.movie.presentation.movie_list.MovieListScreenRoot
import prasad.vennam.tmdb.kmm.feature.movie.presentation.movie_list.MovieListViewmodel

@Composable
@Preview
fun App(engine: HttpClientEngine) {
    MaterialTheme {
        MovieListScreenRoot(
            viewModel = remember {
                MovieListViewmodel(
                    moviesRepository =  DefaultRepository(
                        remoteDataSource = RemoteDataSourceImpl(
                            httpClient = HttpClientFactory.create(engine),
                            apiKey = "9f1c9fef225da8d56317b73f985995b0"
                        )
                    )
                )
            },
            onActionClick = {
            }
        )
    }
}