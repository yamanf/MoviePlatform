package domain.repository

import MovieRemote
import data.model.MoviesResponse
import kotlinx.coroutines.flow.Flow

internal interface MovieRepository {
    suspend fun getMovies(page: Int): Flow<MoviesResponse>

    suspend fun getMovie(movieId: Int): Flow<MovieRemote>
}
