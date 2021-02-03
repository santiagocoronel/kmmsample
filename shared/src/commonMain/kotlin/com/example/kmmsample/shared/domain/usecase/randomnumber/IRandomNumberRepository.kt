package com.example.kmmsample.shared.domain.usecase.randomnumber

import kotlinx.coroutines.flow.Flow

interface IRandomNumberRepository {
    suspend fun getRandomNumber(): Flow<Int>
}