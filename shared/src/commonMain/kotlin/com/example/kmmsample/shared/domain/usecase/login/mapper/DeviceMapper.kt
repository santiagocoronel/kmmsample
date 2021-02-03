package com.example.kmmsample.shared.domain.usecase.login.mapper

import com.example.kmmsample.shared.data.login.dto.DeviceDTO
import com.example.kmmsample.shared.domain.usecase.login.model.Device

object DeviceMapper {

    fun toDeviceDTO(item: Device): DeviceDTO {
        return DeviceDTO(
            fcm = item.firebaseCloudMessagingToken,
            latitude = item.currentLatitude,
            longitude = item.currentLongitude,
            name = item.deviceName,
            client = item.soClient,
            phoneNumber = item.phoneNumber,
            brand = item.brand,
            soid = item.operativeSystemIdentificator
        )

    }
}