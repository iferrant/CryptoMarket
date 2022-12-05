package dev.ferrant.data.extension

import dev.ferrant.data.DataResult

inline fun <reified T> DataResult<T>.onFailure(callback: (error: String?, throwable: Throwable?) -> Unit) {
    if (this is DataResult.Failure) {
        callback(message, throwable)
    }
}

inline fun <reified T> DataResult<T>.onSuccess(callback: (value: T) -> Unit) {
    if (this is DataResult.Success) {
        callback(value)
    }
}
