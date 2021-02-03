package com.example.kmmsample.shared.domain.base

abstract class BasicUseCase<T> {

    @Throws(Exception::class)
    abstract suspend fun execute(): T

}
