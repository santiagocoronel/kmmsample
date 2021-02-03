package com.example.kmmsample.shared.domain.dummy

import com.example.kmmsample.shared.domain.usecase.login.model.Device

object DeviceDummy {

    fun getAndroid(): Device {
        return Device(
            firebaseCloudMessagingToken = "cc3uQOvxRBK7rJOuy6s5i9:APA91bFn1zPPEZ5RZPJzaSkmjh9R50ENWBKs8uHelviLJ6gVG35ciHGvjUrbFZMyXxE7Yvoo_8gvrvwbvOXtcAfv_HC9vGy33sopqSy2VFw2b-JWCbmolLMUO_t7dbPMK9xMal3h7Hci",
            currentLatitude = null,
            currentLongitude = null,
            deviceName = "SM-G9650",
            soClient = "android",
            phoneNumber = null,
            brand = "samsung",
            operativeSystemIdentificator = "faf6ad3b4137ad7b"
        )
    }
}