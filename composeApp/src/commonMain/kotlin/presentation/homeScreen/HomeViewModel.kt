package presentation.homeScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import data.model.MoviesResponse
import data.util.toMovie
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import domain.model.Movie
import domain.usecase.GetMoviesUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    val getMoviesUseCase: GetMoviesUseCase
): ViewModel(){
    var uiState by mutableStateOf(HomeScreenState())
    private var currentPage = 1

    init {
        println("ViewModelCreated")
        loadMovies(forceReload = false)
    }


    fun loadMovies(forceReload: Boolean = false){
        if (uiState.loading) return
        if (forceReload) currentPage = 1
        if (currentPage == 1) uiState = uiState.copy(refreshing = true)

        viewModelScope.launch {
            try {
                var resultMovies = MoviesResponse(listOf()).results
                getMoviesUseCase(page = currentPage).collect{
                    resultMovies = it.results
                }
                val movies:List<Movie> =  if (currentPage == 1) {
                    resultMovies.map {
                        it.toMovie()
                    } 
                }else{
                    uiState.movies + resultMovies.map {
                        it.toMovie()
                    }
                }
                     
                currentPage += 1
                println(resultMovies)
                uiState = uiState.copy(
                    loading = false,
                    refreshing = false,
                    loadFinished = resultMovies.isNotEmpty(),
                    movies = movies
                )

            }catch (error: Throwable){
                println("Could not load movies: ${error.message}")
                uiState = uiState.copy(
                    loading = false,
                    refreshing = false,
                    loadFinished = true,
                    errorMessage = "Could not load movies: ${error.message}"
                )
            }
        }
    }
}




data class HomeScreenState(
    var loading: Boolean = false,
    var refreshing: Boolean = false,
    var movies: List<Movie> = listOf(),
    var errorMessage: String? = null,
    var loadFinished: Boolean = false
)