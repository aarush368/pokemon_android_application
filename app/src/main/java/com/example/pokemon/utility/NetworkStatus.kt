package com.example.pokemon.utility

import okhttp3.ResponseBody

sealed class NetworkStatus<out T> {
    data class Success<out T>(val value: T) : NetworkStatus<T>()
    data class Failure(
        var isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?
    ) : NetworkStatus<Nothing>()
    object Loading : NetworkStatus<Nothing>()
}