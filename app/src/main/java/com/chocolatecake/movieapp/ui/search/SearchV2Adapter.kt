import android.view.LayoutInflater
import android.view.ViewGroup
import com.chocolatecake.movieapp.databinding.ItemMovieHorizontalBinding
import com.chocolatecake.movieapp.databinding.ItemPeopleBinding
import com.chocolatecake.movieapp.databinding.ItemSeasonHorizontalBinding
import com.chocolatecake.movieapp.ui.base.BaseAdapter
import com.chocolatecake.movieapp.ui.search.SearchListener
import com.chocolatecake.movieapp.ui.search.SearchUiState

class SearchV2Adapter(
    private val list: List<SearchUiState.MediaUIState>,
    private val listener: SearchListener
) : BaseAdapter<SearchUiState.MediaUIState>(list, listener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            MOVIE -> {
                val binding =
                    ItemMovieHorizontalBinding.inflate(layoutInflater, parent, false)
                MovieViewHolder(binding)
            }

            TV -> {
                val binding =
                    ItemSeasonHorizontalBinding.inflate(layoutInflater, parent, false)
                TvViewHolder(binding)
            }

            PEOPLE -> {
                val binding = ItemPeopleBinding.inflate(layoutInflater, parent, false)
                PeopleViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val media = list[position]

        when (holder) {
            is MovieViewHolder -> holder.bind(media as SearchUiState.MediaUIState.Movie)
            is TvViewHolder -> holder.bind(media as SearchUiState.MediaUIState.Tv)
            is PeopleViewHolder -> holder.bind(media as SearchUiState.MediaUIState.People)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val media = list[position]
        return when (media.mediaType) {
            SearchUiState.SearchMedia.MOVIE -> MOVIE
            SearchUiState.SearchMedia.TV -> TV
            SearchUiState.SearchMedia.PEOPLE -> PEOPLE
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private companion object {
        private const val MOVIE = 1
        private const val TV = 2
        private const val PEOPLE = 3
    }

    inner class MovieViewHolder(private val binding: ItemMovieHorizontalBinding) :
        BaseViewHolder(binding.root) {

        fun bind(movie: SearchUiState.MediaUIState.Movie) {
            // Bind movie data to the view holder
            binding.movie = movie
            binding.executePendingBindings()

            // Set click listener for movie item
            binding.root.setOnClickListener {
                listener.onMovieClicked(movie)
            }
        }
    }

    inner class TvViewHolder(private val binding: ItemSeasonHorizontalBinding) :
        BaseViewHolder(binding.root) {

        fun bind(tv: SearchUiState.MediaUIState.Tv) {
            // Bind TV data to the view holder
            binding.tv = tv
            binding.executePendingBindings()

            // Set click listener for TV item
            binding.root.setOnClickListener {
                listener.onTvClicked(tv)
            }
        }
    }

    inner class PeopleViewHolder(private val binding: ItemPeopleBinding) :
        BaseViewHolder(binding.root) {

        fun bind(people: SearchUiState.MediaUIState.People) {
            // Bind people data to the view holder
            binding.people = people
            binding.executePendingBindings()

            // Set click listener for people item
            binding.root.setOnClickListener {
                listener.onPeopleClicked(people)
            }
        }
    }
}
