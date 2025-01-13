package prasad.vennam.tmdb.kmm.feature.movie.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieListResponseDto(
    @SerialName("page") val page: Int = 0,
    @SerialName("results") val listOfMovies: List<MovieDto> = listOf(),
    @SerialName("total_pages") val totalPages: Int = 0,
    @SerialName("total_results") val totalResults: Int = 0
)