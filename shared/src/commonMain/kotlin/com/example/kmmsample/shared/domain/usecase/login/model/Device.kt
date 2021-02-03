package com.example.kmmsample.shared.domain.usecase.login.model

import kotlinx.serialization.Serializable

@Serializable
data class Device(
    val firebaseCloudMessagingToken: String? = null,
    val currentLatitude: Double? = null,
    val currentLongitude: Double? = null,
    val deviceName: String,
    val soClient: String,
    val phoneNumber: String? = null,
    val brand: String,
    val operativeSystemIdentificator: String
)