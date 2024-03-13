package data.util

import MovieRemote
import domain.model.Movie

internal fun MovieRemote.toMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        description = overview,
        imageUrl = getImageUrl(posterPath),
        releaseDate = releaseDate
    )
}

private fun getImageUrl(posterPath: String?): String? {
    return posterPath?.let { "https://image.tmdb.org/t/p/w500/$it" }
}