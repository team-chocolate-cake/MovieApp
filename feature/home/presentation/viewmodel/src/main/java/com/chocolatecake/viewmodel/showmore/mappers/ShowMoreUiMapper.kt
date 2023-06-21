package com.chocolatecake.viewmodel.showmore.mappers

import android.util.Log
import com.chocolatecake.entities.GenreEntity
import com.chocolatecake.entities.MovieEntity
import com.chocolatecake.mapper.Mapper
import com.chocolatecake.viewmodel.showmore.ShowMoreUi
import javax.inject.Inject

class ShowMoreUiMapper @Inject constructor() :
    Mapper<MovieEntity, ShowMoreUi> {
    override fun map(input: MovieEntity): ShowMoreUi {
        return ShowMoreUi(
            input.id,
            input.title,
            input.imageUrl,
            input.year,
            convertGenreListToString( input.genreEntities),
            input.rate,
        ).also { Log.e("TAG", "map: $it", ) }
    }

    private fun convertGenreListToString(input: List<GenreEntity>): String {
        return input.joinToString(" | ") { it.genreName }
    }


}