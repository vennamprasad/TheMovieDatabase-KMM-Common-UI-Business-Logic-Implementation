package prasad.vennam.tmdb.kmm.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import prasad.vennam.tmdb.kmm.movie.presentation.movie_details.MovieDetailsScreenRoot
import prasad.vennam.tmdb.kmm.movie.presentation.movie_details.MovieDetailsViewmodel
import prasad.vennam.tmdb.kmm.movie.presentation.movie_list.MovieListScreenRoot
import prasad.vennam.tmdb.kmm.movie.presentation.movie_list.MovieListViewmodel

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Route.MovieGraph) {
            navigation<Route.MovieGraph>(startDestination = Route.MovieList) {
                composable<Route.MovieList> {
                    val viewmodel = koinViewModel<MovieListViewmodel>()
                    MovieListScreenRoot(viewmodel) {
                        navController.navigate(Route.MovieDetail(it.id))
                    }
                }
                composable<Route.MovieDetail> { entry ->
                    val args = entry.toRoute<Route.MovieDetail>()
                    val viewmodel = koinViewModel<MovieDetailsViewmodel>()
                    MovieDetailsScreenRoot(viewmodel, args.id, onBack = {
                        navController.popBackStack()
                    })
                }
            }
        }
    }
}
