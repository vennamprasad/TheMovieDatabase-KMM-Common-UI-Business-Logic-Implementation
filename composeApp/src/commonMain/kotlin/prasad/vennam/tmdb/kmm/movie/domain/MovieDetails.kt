package prasad.vennam.tmdb.kmm.movie.domain

data class MovieDetails(
    val isAdult: Boolean = false,
    val backdropPath: String = "",
    val belongsToCollection: BelongsToCollection? = null,
    val budget: Int = 0,
    val genres: List<Genre> = emptyList(),
    val homepage: String = "",
    val id: Int = 0,
    val imdbId: String = "",
    val originalLanguage: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val posterPath: String = "",
    val productionCompanies: List<ProductionCompany> = emptyList(),
    val productionCountries: List<ProductionCountry> = emptyList(),
    val releaseDate: String = "",
    val revenue: Int = 0,
    val runtime: Int = 0,
    val spokenLanguages: List<SpokenLanguage> = emptyList(),
    val status: String = "",
    val tagline: String = "",
    val title: String = "",
    val hasVideo: Boolean = false,
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0
)

data class BelongsToCollection(
    val id: Int = 0,
    val name: String = "",
    val posterPath: String = "",
    val backdropPath: String = ""
)

data class Genre(
    val id: Int = 0, val name: String = ""
)

data class ProductionCompany(
    val id: Int = 0,
    val logoPath: String = "",
    val name: String = "",
    val originCountry: String = ""
)

data class ProductionCountry(
    val iso31661: String = "", val name: String = ""
)

data class SpokenLanguage(
    val englishName: String = "", val iso6391: String = "", val name: String = ""
)