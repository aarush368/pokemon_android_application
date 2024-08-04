package com.example.pokemon.utility

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

/**
* This fun handle the status of the api call.
* */
suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): NetworkStatus<T> {

    return withContext(Dispatchers.IO) {
        try {
            NetworkStatus.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            when (throwable) {
                is HttpException -> {
                    NetworkStatus.Failure(
                        false,
                        throwable.code(),
                        throwable.response()?.errorBody()
                    )
                }
                else -> {
                    NetworkStatus.Failure(true, null, null)
                }
            }
        }
    }
}
