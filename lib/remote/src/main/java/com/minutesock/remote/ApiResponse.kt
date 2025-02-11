package com.minutesock.remote

sealed class ApiResponse<out T> {
    data class Success<T>(val data: T): ApiResponse<T>()
    data class Error(val message: String, val statusCode: Int): ApiResponse<Nothing>()
}

fun ApiResponse<*>.isSuccessful(): Boolean {
    return when (this) {
        is ApiResponse.Error -> false
        is ApiResponse.Success -> true
    }
}

inline fun <T> ApiResponse<T>.onSuccess(successBlock: (data: T) -> Unit) {
    when (this) {
        is ApiResponse.Error -> Unit
        is ApiResponse.Success -> successBlock(data)
    }
}