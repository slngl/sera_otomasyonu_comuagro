package com.agrocomu.seraotomasyonu.entity.base

sealed class DataHolder<out T> {
    data class Success<out T>(val data: T) : DataHolder<T>()
    data class Error(val exception: Throwable?, val message: String = "An error occured") :
        DataHolder<Nothing>()
}

inline suspend fun <T,Z> DataHolder<T>.mapAndResponseIfSuccess(mapper: (T)->(Z)): DataHolder<Z> {
    return when (this) {
        is DataHolder.Success -> {
            println("Which Thread On UseCase  ${Thread.currentThread()}")
            DataHolder.Success(mapper(this.data))
        }
        is DataHolder.Error -> {
            println("Which Thread On UseCase  ${Thread.currentThread()}")
            this
        }
    }
}
