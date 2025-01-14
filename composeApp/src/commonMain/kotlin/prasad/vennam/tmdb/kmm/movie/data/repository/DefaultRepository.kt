package prasad.vennam.tmdb.kmm.movie.data.repository

import prasad.vennam.tmdb.kmm.core.domain.DataError
import prasad.vennam.tmdb.kmm.core.domain.NetworkResult
import prasad.vennam.tmdb.kmm.core.domain.map
import prasad.vennam.tmdb.kmm.movie.data.mappers.toMovie
import prasad.vennam.tmdb.kmm.movie.data.mappers.toMovieDetails
import prasad.vennam.tmdb.kmm.movie.data.network.RemoteDataSource
import prasad.vennam.tmdb.kmm.movie.domain.Movie
import prasad.vennam.tmdb.kmm.movie.domain.MovieDetails
import prasad.vennam.tmdb.kmm.movie.domain.MovieRepository

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

    override suspend fun getMovieDetails(
        movieId: Int,
    ): NetworkResult<MovieDetails, DataError.Remote> {
        return remoteDataSource
            .getMovieDetails(movieId)
            .map { it.toMovieDetails() }
    }
}