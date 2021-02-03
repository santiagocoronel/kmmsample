package com.example.kmmsample.androidApp

import androidx.lifecycle.*
import com.example.kmmsample.androidApp.base.BaseViewModel
import com.example.kmmsample.shared.domain.dummy.DeviceDummy
import com.example.kmmsample.shared.domain.usecase.login.LoginUseCase
import com.example.kmmsample.shared.domain.usecase.login.model.Device
import com.example.kmmsample.shared.domain.usecase.login.model.UserProfile
import com.example.kmmsample.shared.domain.usecase.randomnumber.RandomNumberUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(
    private val randomNumberUseCase: RandomNumberUseCase,
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    private val _randomNumber: MutableLiveData<Int> = MutableLiveData()
    val randomNumber: LiveData<Int> = _randomNumber

    private val _userProfile: MutableLiveData<UserProfile> = MutableLiveData()
    val userProfile: LiveData<UserProfile> = _userProfile

    fun executeGetRandomNumber() {

        viewModelScope.launch {
            randomNumberUseCase.execute()
                .catch {
                    _errorUnExpected.value = true
                }.collect {
                    _randomNumber.value = it
                }
        }

    }

    fun executeGetUserProfile() {
        viewModelScope.launch {
            loginUseCase.bind(
                "drogocoronel@hotmail.com",
                "HkiNj2tEmCUSanSjGsYkOg==",
                DeviceDummy.getAndroid()
            )
            loginUseCase.execute()
                .catch {
                    _errorUnExpected.value = true
                }.collect {
                    _userProfile.value = it
                }
        }

    }


    class MainViewModelFactory(
        private val randomNumberUseCase: RandomNumberUseCase,
        private val loginUseCase: LoginUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(
                RandomNumberUseCase::class.java,
                LoginUseCase::class.java
            ).newInstance(randomNumberUseCase, loginUseCase)
        }
    }
}