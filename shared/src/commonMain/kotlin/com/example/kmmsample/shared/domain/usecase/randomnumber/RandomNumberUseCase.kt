package com.example.kmmsample.shared.domain.usecase.randomnumber

import com.example.kmmsample.shared.domain.base.BasicUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RandomNumberUseCase(private val repository: IRandomNumberRepository) :
    BasicUseCase<Flow<Int>>() {

    private var randomNumberJob: Job? = null
    private val coroutineScope: CoroutineScope = MainScope()

    @Throws(Exception::class)
    override suspend fun execute(): Flow<Int> {
        return repository.getRandomNumber()
    }

    @Throws(Exception::class)
    suspend fun executeByJob(success: (Int) -> Unit) {
        randomNumberJob = coroutineScope.launch {
            repository.getRandomNumber()
                .catch { throw Exception(it.message) }
                .collect {
                    success(it)
                }
        }
    }

}
