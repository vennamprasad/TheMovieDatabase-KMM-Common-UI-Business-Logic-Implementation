package prasad.vennam.tmdb.kmm.movie.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsDto(
    @SerialName("adult")
    val adult: Boolean = false,
    @SerialName("backdrop_path")
    val backdropPath: String = "",
    @SerialName("belongs_to_collection")
    val belongsToCollectionDtos: BelongsToCollectionDto = BelongsToCollectionDto(),
    @SerialName("budget")
    val budget: Int = 0,
    @SerialName("genres")
    val genreDtos: List<GenreDto> = listOf(),
    @SerialName("homepage")
    val homepage: String = "",
    @SerialName("id")
    val id: Int = 0,
    @SerialName("imdb_id")
    val imdbId: String = "",
    @SerialName("origin_country")
    val originCountry: List<String> = listOf(),
    @SerialName("original_language")
    val originalLanguage: String = "",
    @SerialName("original_title")
    val originalTitle: String = "",
    @SerialName("overview")
    val overview: String = "",
    @SerialName("popularity")
    val popularity: Double = 0.0,
    @SerialName("poster_path")
    val posterPath: String = "",
    @SerialName("production_companies")
    val productionCompaniesDtos: List<ProductionCompanyDto> = listOf(),
    @SerialName("production_countries")
    val productionCountriesDtos: List<ProductionCountryDto> = listOf(),
    @SerialName("release_date")
    val releaseDate: String = "",
    @SerialName("revenue")
    val revenue: Int = 0,
    @SerialName("runtime")
    val runtime: Int = 0,
    @SerialName("spoken_languages")
    val spokenLanguageDtos: List<SpokenLanguageDto> = listOf(),
    @SerialName("status")
    val status: String = "",
    @SerialName("tagline")
    val tagline: String = "",
    @SerialName("title")
    val title: String = "",
    @SerialName("video")
    val video: Boolean = false,
    @SerialName("vote_average")
    val voteAverage: Double = 0.0,
    @SerialName("vote_count")
    val voteCount: Int = 0
)

@Serializable
data class SpokenLanguageDto(
    @SerialName("english_name") val englishName: String = "",
    @SerialName("iso_639_1") val iso6391: String = "",
    @SerialName("name") val name: String = ""
)

@Serializable
data class ProductionCountryDto(
    @SerialName("iso_3166_1") val iso31661: String = "",
    @SerialName("name") val name: String = ""
)

@Serializable
data class ProductionCompanyDto(
    @SerialName("id") val id: Int = 0,
    @SerialName("logo_path") val logoPath: String = "",
    @SerialName("name") val name: String = "",
    @SerialName("origin_country") val originCountry: String = ""
)


@Serializable
data class GenreDto(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("name")
    val name: String = ""
)

@Serializable
data class BelongsToCollectionDto(
    @SerialName("backdrop_path")
    val backdropPath: String = "",
    @SerialName("id")
    val id: Int = 0,
    @SerialName("name")
    val name: String = "",
    @SerialName("poster_path")
    val posterPath: String = ""
)