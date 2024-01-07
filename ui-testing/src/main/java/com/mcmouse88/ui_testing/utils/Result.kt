package com.mcmouse88.ui_testing.utils

import kotlinx.coroutines.CancellationException

sealed class Result<out S, out E> {
    data class Success<out S>(val data: S) : Result<S, Nothing>()
    data class Error<out E>(val error: E) : Result<Nothing, E>()
}

inline fun <S, E, R> Result<S, E>.mapSuccess(block: (S) -> R): Result<R, E> {
    return when (this) {
        is Result.Success -> Result.Success(data = block(this.data))
        is Result.Error -> Result.Error(error = this.error)
    }
}

inline fun <S, E> Result<S, E>.doOnSuccess(block: (S) -> Unit): Result<S, E> {
    if (this is Result.Success) {
        block.invoke(this.data)
    }
    return this
}

inline fun <S, E> Result<S, E>.doOnError(block: (E) -> Unit): Result<S, E> {
    if (this is Result.Error) {
        block.invoke(this.error)
    }
    return this
}

inline fun <S, R> S.runOperationCatching(block: S.() -> R): Result<R, Throwable> {
    return try {
        Result.Success(block())
    } catch (e: CancellationException) {
        throw e
    } catch (e: Throwable) {
        Result.Error(e)
    }
}