package com.example.kmmsample.shared.domain.usecase.login

import com.example.kmmsample.shared.domain.base.BasicUseCase
import com.example.kmmsample.shared.domain.usecase.login.mapper.DeviceMapper
import com.example.kmmsample.shared.domain.usecase.login.model.Device
import com.example.kmmsample.shared.domain.usecase.login.model.UserProfile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginUseCase(private val repository: ILoginRepository) : BasicUseCase<Flow<UserProfile>>() {


    private var userProfileJob: Job? = null
    private val coroutineScope: CoroutineScope = MainScope()


    private lateinit var email: String
    private lateinit var encryptedPassword: String
    private lateinit var device: Device

    fun bind(email: String, encryptedPassword: String, device: Device) {
        this.email = email
        this.encryptedPassword = encryptedPassword
        this.device = device
    }

    private fun validate() {
        if (!this::email.isInitialized or !this::encryptedPassword.isInitialized or !this::device.isInitialized) {
            throw Exception("first you must initlize by the method bind")
        }
    }

    @Throws(Exception::class)
    override suspend fun execute(): Flow<UserProfile> {
        validate()
        return repository.login(
            email = email,
            password = encryptedPassword,
            DeviceMapper.toDeviceDTO(device)
        )
    }

    @Throws(Exception::class)
    fun executeByJob(success: (UserProfile) -> Unit) {
        validate()
        userProfileJob = coroutineScope.launch {
            repository.login(
                email = email,
                password = encryptedPassword,
                DeviceMapper.toDeviceDTO(device)
            ).collect {
                success(it)
            }
        }
    }


}