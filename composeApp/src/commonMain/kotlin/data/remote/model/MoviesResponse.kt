package data.model

import MovieRemote
import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponse(
    val results: List<MovieRemote>
)