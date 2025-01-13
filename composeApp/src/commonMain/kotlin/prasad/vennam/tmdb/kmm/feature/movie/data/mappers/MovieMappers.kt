package prasad.vennam.tmdb.kmm.feature.movie.data.mappers

import prasad.vennam.tmdb.kmm.feature.movie.data.dto.MovieDto
import prasad.vennam.tmdb.kmm.feature.movie.domain.Movie

fun MovieDto.toMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath,
        backdropPath = backdropPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        voteCount = voteCount,
        popularity = popularity,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        isAdult = adult,
        isVideo = video,
        genreIds = genreIds,
    )
}