package com.example.kmmsample.shared.data.login.dto

import kotlinx.serialization.Serializable

@Serializable
data class AccountDTO(
    var id: Long? = null,
    var createdate: String? = null,
    var dni: Long? = null,
    var lastname: String? = null,
    var firstname: String? = null,
    var birthdate: String? = null,
    var email: String? = null,
    var emailVerificated: Boolean? = false,
    var firebasetoken: String? = null,
    var rptoken: String? = null,
    var address: String? = null,
    var latitude: Double? = null,
    var longitude: Double? = null,
    var sex: Boolean? = null,
    var avatar: String? = null,
    var accountVerificated: Boolean = false,
    var nick: String? = null
)