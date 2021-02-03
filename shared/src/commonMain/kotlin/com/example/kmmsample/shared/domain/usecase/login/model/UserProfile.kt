package com.example.kmmsample.shared.domain.usecase.login.model

import kotlinx.serialization.Serializable

@Serializable
data class UserProfile(
    var lastname: String? = null,
    var firstname: String? = null,
    var avatar: String? = null,
    var nick: String? = null,
    var email: String? = null
)