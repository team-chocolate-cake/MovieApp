import com.chocolatecake.entities.movieDetails.StatusEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class AddToWatchList @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movieId:Int,): StatusEntity {
        return movieRepository.addWatchlist(movieId,"movie",true)
    }
}