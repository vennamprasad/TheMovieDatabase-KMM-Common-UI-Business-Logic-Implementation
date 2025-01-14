package prasad.vennam.tmdb.kmm.movie.data.network

import prasad.vennam.tmdb.kmm.core.domain.DataError
import prasad.vennam.tmdb.kmm.core.domain.NetworkResult
import prasad.vennam.tmdb.kmm.movie.data.dto.MovieDetailsDto
import prasad.vennam.tmdb.kmm.movie.data.dto.MovieListResponseDto

interface RemoteDataSource {
    suspend fun searchMovies(
        query: String? = "",
        language: String? = "en-US",
        isAdult: Boolean? = false,
        page: Int? = 1,
        region: String? = "US",
    ): NetworkResult<MovieListResponseDto, DataError.Remote>

    suspend fun getMovieDetails(
        movieId: Int,
    ): NetworkResult<MovieDetailsDto, DataError.Remote>
}