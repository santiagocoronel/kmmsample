package com.example.kmmsample.shared.data.login.mapper

import com.example.kmmsample.shared.data.login.response.LoginResponse
import com.example.kmmsample.shared.domain.usecase.login.model.UserProfile

object LoginResponseMapper {

    fun toUserProfile(response: LoginResponse): UserProfile {
        return UserProfile().apply {
            lastname = response.account?.lastname
            firstname = response.account?.firstname
            avatar = response.account?.avatar
            nick = response.account?.nick
            email = response.account?.email
        }
    }

}