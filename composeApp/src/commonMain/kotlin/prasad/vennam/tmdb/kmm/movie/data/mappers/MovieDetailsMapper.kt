package prasad.vennam.tmdb.kmm.movie.data.mappers

import prasad.vennam.tmdb.kmm.movie.data.dto.BelongsToCollectionDto
import prasad.vennam.tmdb.kmm.movie.data.dto.GenreDto
import prasad.vennam.tmdb.kmm.movie.data.dto.MovieDetailsDto
import prasad.vennam.tmdb.kmm.movie.data.dto.ProductionCompanyDto
import prasad.vennam.tmdb.kmm.movie.data.dto.ProductionCountryDto
import prasad.vennam.tmdb.kmm.movie.data.dto.SpokenLanguageDto
import prasad.vennam.tmdb.kmm.movie.domain.BelongsToCollection
import prasad.vennam.tmdb.kmm.movie.domain.Genre
import prasad.vennam.tmdb.kmm.movie.domain.MovieDetails
import prasad.vennam.tmdb.kmm.movie.domain.ProductionCompany
import prasad.vennam.tmdb.kmm.movie.domain.ProductionCountry
import prasad.vennam.tmdb.kmm.movie.domain.SpokenLanguage

fun MovieDetailsDto.toMovieDetails(): MovieDetails {
    return MovieDetails(
        isAdult = adult,
        backdropPath = backdropPath,
        belongsToCollection = belongsToCollectionDtos.toBelongsToCollection(),
        budget = budget,
        genres = genreDtos.toGenres(),
        homepage = homepage,
        id = id,
        imdbId = imdbId,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        productionCompanies = productionCompaniesDtos.toProductionCompanies(),
        productionCountries = productionCountriesDtos.toProductionCountries(),
        releaseDate = releaseDate,
        revenue = revenue,
        runtime = runtime,
        spokenLanguages = spokenLanguageDtos.toSpokenLanguages(),
        status = status,
        tagline = tagline,
        title = title,
        hasVideo = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
    )
}

fun BelongsToCollectionDto.toBelongsToCollection(): BelongsToCollection {
    return BelongsToCollection(
        id = id,
        name = name,
        posterPath = posterPath,
        backdropPath = backdropPath,
    )
}

fun List<BelongsToCollectionDto>.toBelongsToCollection(): List<BelongsToCollection> {
    return map { it.toBelongsToCollection() }
}


fun GenreDto.toGenre(): Genre {
    return Genre(
        id = id,
        name = name,
    )
}

fun List<GenreDto>.toGenres(): List<Genre> {
    return map { it.toGenre() }
}

fun ProductionCompanyDto.toProductionCompany(): ProductionCompany {
    return ProductionCompany(
        id = id,
        logoPath = logoPath,
        name = name,
        originCountry = originCountry,
    )
}

fun List<ProductionCompanyDto>.toProductionCompanies(): List<ProductionCompany> {
    return map { it.toProductionCompany() }
}

fun ProductionCountryDto.toProductionCountry(): ProductionCountry {
    return ProductionCountry(
        iso31661 = iso31661,
        name = name,
    )
}

fun List<ProductionCountryDto>.toProductionCountries(): List<ProductionCountry> {
    return map { it.toProductionCountry() }
}

fun SpokenLanguageDto.toSpokenLanguage(): SpokenLanguage {
    return SpokenLanguage(
        englishName = englishName,
        iso6391 = iso6391,
        name = name,
    )
}

fun List<SpokenLanguageDto>.toSpokenLanguages(): List<SpokenLanguage> {
    return map { it.toSpokenLanguage() }
}


