package data.remote.repository

import MovieRemote
import data.model.MoviesResponse
import data.remote.RemoteDataSource
import domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

internal class MovieRepositoryImpl(
    private val remoteDateSource: RemoteDataSource
): MovieRepository {

    override suspend fun getMovies(page: Int): Flow<MoviesResponse> {
        return remoteDateSource.getMovies(page = page)
    }

    override suspend fun getMovie(movieId: Int): Flow<MovieRemote> {
        return remoteDateSource.getMovie(movieId = movieId)
    }
}