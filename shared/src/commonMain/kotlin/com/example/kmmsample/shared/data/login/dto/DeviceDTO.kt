package com.example.kmmsample.shared.data.login.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeviceDTO(
    val fcm: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val name: String,
    val client: String,
    @SerialName("phonenumber")
    val phoneNumber: String? = null,
    val brand: String,
    val soid: String
)