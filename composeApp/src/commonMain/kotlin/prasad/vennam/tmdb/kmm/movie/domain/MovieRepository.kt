package prasad.vennam.tmdb.kmm.movie.domain

import prasad.vennam.tmdb.kmm.core.domain.DataError
import prasad.vennam.tmdb.kmm.core.domain.NetworkResult

interface MovieRepository {
    suspend fun searchMovies(
        query: String,
        language: String,
        isAdult: Boolean,
        page: Int,
        region: String,
    ): NetworkResult<List<Movie>, DataError.Remote>

    suspend fun getMovieDetails(
        movieId: Int,
    ): NetworkResult<MovieDetails, DataError.Remote>
}