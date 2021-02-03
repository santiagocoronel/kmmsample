package com.example.kmmsample.androidApp.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    protected val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading

    protected val _errorConnection: MutableLiveData<Boolean> = MutableLiveData()
    val errorConnection: LiveData<Boolean> = _errorConnection

    protected val _errorTimeOut: MutableLiveData<Boolean> = MutableLiveData()
    val errorTimeOut: LiveData<Boolean> = _errorTimeOut

    protected val _errorUnExpected: MutableLiveData<Boolean> = MutableLiveData()
    val errorUnExpected: LiveData<Boolean> = _errorUnExpected

    fun startLoading() {
        _loading.value = true
    }

    fun stopLoading() {
        _loading.value = false
    }

    fun startErrorConnection() {
        _errorConnection.value = true
    }

    fun stopErrorConnection() {
        _errorConnection.value = false
    }

    fun startErrorTimeOut() {
        _errorTimeOut.value = true
    }

    fun stopErrorTimeOut() {
        _errorTimeOut.value = false
    }

    fun startErrorUnExpected() {
        _errorTimeOut.value = true
    }

    fun stopErrorUnExpected() {
        _errorTimeOut.value = false
    }
}
