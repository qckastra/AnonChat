package com.soumeswar.anonchat.data

sealed class OnionResult<out T> {

    data class Success<T>(val data: T) : OnionResult<T>()

    data class Error(
        val reason: String,
        val throwable: Throwable? = null
    ) : OnionResult<Nothing>()

    object Loading : OnionResult<Nothing>()
}