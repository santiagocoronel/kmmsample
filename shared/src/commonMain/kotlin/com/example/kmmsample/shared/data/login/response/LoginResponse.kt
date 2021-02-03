package com.example.kmmsample.shared.data.login.response

import com.example.kmmsample.shared.data.login.dto.AccountDTO
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    var token: String? = null,
    var account: AccountDTO? = null
)