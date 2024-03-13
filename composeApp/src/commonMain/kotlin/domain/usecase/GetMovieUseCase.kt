package domain.usecase

import MovieRemote
import domain.model.Movie
import domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetMovieUseCase: KoinComponent {
    private val repository: MovieRepository by inject()

    @Throws(Exception::class)
    suspend operator fun invoke(movieId: Int): Flow<MovieRemote> {
        return repository.getMovie(movieId = movieId)
    }
}