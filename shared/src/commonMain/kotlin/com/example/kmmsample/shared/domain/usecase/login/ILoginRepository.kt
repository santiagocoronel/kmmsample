package com.example.kmmsample.shared.domain.usecase.login

import com.example.kmmsample.shared.data.login.dto.DeviceDTO
import com.example.kmmsample.shared.domain.usecase.login.model.UserProfile
import kotlinx.coroutines.flow.Flow

interface ILoginRepository {
    suspend fun login(
        email: String,
        password: String,
        device: DeviceDTO
    ): Flow<UserProfile>
}