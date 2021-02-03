package com.example.kmmsample.shared.data.login.base

sealed class Response<out T> {
    data class Success<out T>(val successData: T) : Response<T>()
    data class Failure<out R : Throwable>(val errorData: R) : Response<Nothing>()
}