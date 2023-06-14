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
    @ApplicationContext private val context: Context
) : StringsRes {
    override val theRequestFailed: String = getString(R.string.the_request_failed)

    override val passwordIsRequired: String = getString(R.string.password_is_required)

    override val usernameIsRequired: String = getString(R.string.username_is_required)


    private fun getString(@StringRes stringsRes: Int): String {
        return context.getString(stringsRes)
    }

    private fun getString(@StringRes stringsRes: Int, vararg args: Any): String {
        return context.getString(stringsRes, args)
    }
}