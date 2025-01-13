package prasad.vennam.tmdb.kmm.feature.movie.data.network

import prasad.vennam.tmdb.kmm.core.domain.DataError
import prasad.vennam.tmdb.kmm.core.domain.NetworkResult
import prasad.vennam.tmdb.kmm.feature.movie.data.dto.MovieListResponseDto

interface RemoteDataSource {
    suspend fun searchMovies(
        query: String? = "",
        language: String? = "en-US",
        isAdult: Boolean? = false,
        page: Int? = 1,
        region: String? = "US",
    ): NetworkResult<MovieListResponseDto, DataError.Remote>
}