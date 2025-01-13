package prasad.vennam.tmdb.kmm.feature.movie.domain

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
}