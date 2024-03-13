package domain.usecase

import data.model.MoviesResponse
import domain.model.Movie
import domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetMoviesUseCase: KoinComponent {
    private val repository: MovieRepository by inject()

    @Throws(Exception::class)
    suspend operator fun invoke(page: Int): Flow<MoviesResponse> {
        return repository.getMovies(page = page)
    }
}