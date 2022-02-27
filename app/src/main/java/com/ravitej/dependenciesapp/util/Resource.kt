package com.ravitej.dependenciesapp.util

data class Resource<out T>(val status: Status<T>) {

    companion object {
        fun <T> success(data: T) = Resource(Success(data))
        fun <T> error(message: String, data: T? = null) = Resource(Error(message, data))
        fun loading() = Resource(Loading)
    }
}

sealed class Status<out T>(val data: T? = null)
class Success<T>(data: T) : Status<T>(data)
class Error<T>(val message: String, data: T? = null) : Status<T>(data)
object Loading : Status<Nothing>()
