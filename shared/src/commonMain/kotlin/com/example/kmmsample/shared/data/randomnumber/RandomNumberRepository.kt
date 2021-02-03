package com.example.kmmsample.shared.data.randomnumber

import com.example.kmmsample.shared.domain.usecase.randomnumber.IRandomNumberRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

class RandomNumberRepository : IRandomNumberRepository {

    override suspend fun getRandomNumber(): Flow<Int> = flow {
        emit(Random.nextInt())
    }
}