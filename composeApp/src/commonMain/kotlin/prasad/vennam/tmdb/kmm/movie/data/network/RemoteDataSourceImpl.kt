package prasad.vennam.tmdb.kmm.movie.data.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import prasad.vennam.tmdb.kmm.core.data.safeCall
import prasad.vennam.tmdb.kmm.core.domain.DataError
import prasad.vennam.tmdb.kmm.core.domain.NetworkResult
import prasad.vennam.tmdb.kmm.movie.data.dto.MovieDetailsDto
import prasad.vennam.tmdb.kmm.movie.data.dto.MovieListResponseDto

const val BASE_URL = "https://api.themoviedb.org/3/"

class RemoteDataSourceImpl(
    private val httpClient: HttpClient
) : RemoteDataSource {
    override suspend fun searchMovies(
        query: String?,
        language: String?,
        isAdult: Boolean?,
        page: Int?,
        region: String?
    ): NetworkResult<MovieListResponseDto, DataError.Remote> {
        return safeCall<MovieListResponseDto> {
            httpClient.get(BASE_URL + "search/movie") {
                parameter("api_key", "9f1c9fef225da8d56317b73f985995b0")
                parameter("query", query)
                parameter("language", language)
                parameter("include_adult", isAdult)
                parameter("page", page)
                parameter("region", region)
            }
        }
    }

    override suspend fun getMovieDetails(
        movieId: Int,
    ): NetworkResult<MovieDetailsDto, DataError.Remote> {
        return safeCall<MovieDetailsDto> {
            httpClient.get(BASE_URL + "movie/$movieId") {
                parameter("api_key", "9f1c9fef225da8d56317b73f985995b0")
            }
        }
    }
}