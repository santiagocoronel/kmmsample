package com.example.kmmsample.shared.data.login.network

import com.example.kmmsample.shared.data.login.base.Response
import com.example.kmmsample.shared.data.login.dto.DeviceDTO
import com.example.kmmsample.shared.data.login.request.LoginRequest
import com.example.kmmsample.shared.data.login.response.LoginResponse
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class Api {

    private val baseUrl = "http://18.222.133.138:80"

    private val basicAuthToken =
        "4sQslfqchgBvFSdEE14UlCoCf3ddrrjB8DpVxPopWsxmvEJ3SnqBPYVmrzf7YA9kGfA80Az1I2blc6g+KeGc0QEgRo8v6lfTrop9UKqtZeiT4H46AQ6OaKugMUA9jgUIWnK/3zFWHkov1vRBNds1QekmkJ9I2BrVMsIjZTAspek="

    private val client by lazy {
        HttpClient {
            install(JsonFeature) {
                serializer = KotlinxSerializer(json = kotlinx.serialization.json.Json {
                    isLenient = false
                    ignoreUnknownKeys = true
                    allowSpecialFloatingPointValues = true
                    useArrayPolymorphism = false
                })
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
    }

    @Throws(Exception::class)
    suspend fun login(
        email: String,
        password: String,
        device: DeviceDTO
    ): Flow<Response<LoginResponse>> = flow {
        val networkResponse = client.post<LoginResponse> {
            url("$baseUrl/v1/account/login")
            method = HttpMethod.Post
            headers {
                append("basic_auth", basicAuthToken)
            }
            contentType(ContentType.parse("application/json"))
            body = LoginRequest(email, password, device)
        }
        emit(Response.Success(networkResponse) as Response<LoginResponse>)
    }.catch {
        emit(Response.Failure(it))
    }

}