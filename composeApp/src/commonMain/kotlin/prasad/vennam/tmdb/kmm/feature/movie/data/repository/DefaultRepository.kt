package prasad.vennam.tmdb.kmm.feature.movie.data.repository

import prasad.vennam.tmdb.kmm.core.domain.DataError
import prasad.vennam.tmdb.kmm.core.domain.NetworkResult
import prasad.vennam.tmdb.kmm.core.domain.map
import prasad.vennam.tmdb.kmm.feature.movie.data.mappers.toMovie
import prasad.vennam.tmdb.kmm.feature.movie.data.network.RemoteDataSource
import prasad.vennam.tmdb.kmm.feature.movie.domain.Movie
import prasad.vennam.tmdb.kmm.feature.movie.domain.MovieRepository

class DefaultRepository(
    private val remoteDataSource: RemoteDataSource,
) : MovieRepository {
    override suspend fun searchMovies(
        query: String,
        language: String,
        isAdult: Boolean,
        page: Int,
        region: String,
    ): NetworkResult<List<Movie>, DataError.Remote> {
        return remoteDataSource
            .searchMovies(query, language, isAdult, page, region)
            .map { dto ->
                dto.listOfMovies.map { it.toMovie() }
            }
    }
}