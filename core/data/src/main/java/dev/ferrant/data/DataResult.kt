package dev.ferrant.data

sealed class DataResult<out T> {
    data class Success<out R>(val value: R): DataResult<R>()
    data class Failure(
        val message: String?,
        val throwable: Throwable?,
    ): DataResult<Nothing>()
}
