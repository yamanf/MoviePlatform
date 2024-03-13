import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import domain.usecase.GetMoviesUseCase
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview
import presentation.homeScreen.HomeScreen
import presentation.homeScreen.HomeViewModel

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        val homeViewModel: HomeViewModel = HomeViewModel(getMoviesUseCase = GetMoviesUseCase())
        HomeScreen(
            uiState = homeViewModel.uiState,
            loadNextMovies = {homeViewModel.loadMovies(forceReload = it)})
    }
}