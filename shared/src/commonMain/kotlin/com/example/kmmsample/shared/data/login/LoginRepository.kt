package com.example.kmmsample.shared.data.login

import com.example.kmmsample.shared.data.login.base.Response
import com.example.kmmsample.shared.data.login.dto.DeviceDTO
import com.example.kmmsample.shared.data.login.mapper.LoginResponseMapper
import com.example.kmmsample.shared.data.login.network.Api
import com.example.kmmsample.shared.data.login.response.LoginResponse
import com.example.kmmsample.shared.domain.usecase.login.ILoginRepository
import com.example.kmmsample.shared.domain.usecase.login.model.UserProfile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class LoginRepository(private val api: Api) : ILoginRepository {

    @Throws(Exception::class)
    override suspend fun login(
        email: String,
        password: String,
        device: DeviceDTO
    ): Flow<UserProfile> = flow {
        val response = api.login(email = email, password = password, device = device)

        response.collect {
            when (it) {
                is Response.Success<LoginResponse> -> {
                    emit(LoginResponseMapper.toUserProfile(it.successData))
                }
                is Response.Failure<Throwable> -> {
                    //should handle error
                    throw Exception("something is wrong, please try later")
                }
            }
        }
    }

}