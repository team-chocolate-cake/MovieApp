package com.chocolatecake.movieapp.data.repository.base

import com.chocolatecake.movieapp.data.remote.response.DataWrapperResponse
import retrofit2.Response
import java.net.UnknownHostException

abstract class BaseRepository {

    protected suspend fun <T> wrapApiCall(call: suspend () -> Response<T>): T {
        return try {
            call().takeIf { it.isSuccessful }?.body() ?: throw UnauthorizedThrowable()
        } catch (e: UnknownHostException) {
            throw NoNetworkThrowable()
        } catch (e: Exception) {
            throw Throwable(e.message)
        }
    }

    protected suspend fun <INPUT, OUTPUT> refreshWrapper(
        apiCall: suspend () -> Response<DataWrapperResponse<INPUT>>,
        localMapper: (INPUT) -> OUTPUT,
        databaseSaver: suspend (List<OUTPUT>) -> Unit,
        clearOldLocalData: (suspend () -> Unit)? = null,
    ) {
        try {
            wrapApiCall(apiCall).results
                ?.filterNotNull()
                ?.let {
                    clearOldLocalData?.invoke()
                    databaseSaver(it.map { item -> localMapper(item) })
                }
        } catch (_: Throwable) {
        }
    }
}