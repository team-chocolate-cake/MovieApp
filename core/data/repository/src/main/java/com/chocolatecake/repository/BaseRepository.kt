package com.chocolatecake.repository

import com.chocolatecake.remote.response.DataWrapperResponse
import retrofit2.Response
import java.net.UnknownHostException

abstract class BaseRepository {

    protected suspend fun <T> wrapApiCall(call: suspend () -> Response<T>): T {
        return try {
            val result = call()
            if (result.code() == UNAUTHORIZED_CODE) {
                throw UnauthorizedThrowable()
            }
            if (result.code() == TIMEOUT_CODE) {
                throw TimeoutThrowable()
            }
            result.body() ?: throw ParsingThrowable()
        } catch (e: UnknownHostException) {
            throw NoNetworkThrowable()
        } catch (e: Exception) {
            throw ApiThrowable(e.message)
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

    private companion object {
        const val UNAUTHORIZED_CODE = 401
        const val TIMEOUT_CODE = 401
    }
}