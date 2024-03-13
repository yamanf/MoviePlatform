package data.remote

import kotlinx.coroutines.flow.flow

internal class RemoteDataSource(
    private val apiService: MovieService,
) {

    suspend fun getMovies(page: Int) = flow {
        emit(apiService.getMovies(page = page))
    }

    suspend fun getMovie(movieId: Int) = flow {
        emit(apiService.getMovie(movieId = movieId))
    }
}