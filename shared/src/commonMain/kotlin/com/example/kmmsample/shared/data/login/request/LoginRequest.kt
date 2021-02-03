package com.example.kmmsample.shared.data.login.request

import com.example.kmmsample.shared.data.login.dto.DeviceDTO
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val email: String,
    val encryptedPassword: String,
    val device: DeviceDTO
)