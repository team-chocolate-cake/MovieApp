package com.chocolatecake.movieapp.resourses_helper

import android.content.Context
import androidx.annotation.StringRes
import com.chocolatecake.bases.R
import com.chocolatecake.bases.StringsRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StringsResImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : StringsRes {
    override val theRequestFailed: String = getString(R.string.the_request_failed)

    override val passwordIsRequired: String = getString(R.string.password_is_required)

    override val usernameIsRequired: String = getString(R.string.username_is_required)

    override val duplicateEntity: String = getString(R.string.duplicate_entry)

    override val someThingError: String = getString(R.string.some_thing_error)

    override val addSuccessfully: String = getString(R.string.added_successfully)

    override val newListAddSuccessFully: String =
        getString(R.string.new_list_was_added_successfully)

    override val ratingAddSuccessFully: String = getString(R.string.rating_was_added_successfully)

    override val someThingErrorWhenAddRating: String =
        getString(R.string.something_went_wrong_please_try_again_later)

    override val easy: String = getString(R.string.easy)

    override val medium: String = getString(R.string.medium)

    override val hard: String = getString(R.string.hard)
    override val watchlist: String = getString(com.chocolatecake.ui.home.R.string.watchlist)
    override val favourite: String = getString(com.chocolatecake.ui.home.R.string.favorite)

    override val popularMovies: String = getString(R.string.popular)
    override val topRatedMovies: String = getString(R.string.top_rated)
    override val trending: String = getString(R.string.trending)

    override val timeOut: String =getString(R.string.time_out)

    override val today: String = getString(R.string.today)

    override val yesterday: String = getString(R.string.yesterday)

    private fun getString(@StringRes stringsRes: Int): String {
        return context.getString(stringsRes)
    }

    private fun getString(@StringRes stringsRes: Int, vararg args: Any): String {
        return context.getString(stringsRes, args)
    }
}