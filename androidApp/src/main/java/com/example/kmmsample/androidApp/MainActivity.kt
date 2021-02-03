package com.example.kmmsample.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kmmsample.shared.Greeting
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.kmmsample.androidApp.databinding.ActivityMainBinding
import com.example.kmmsample.shared.data.login.LoginRepository
import com.example.kmmsample.shared.data.login.network.Api
import com.example.kmmsample.shared.data.randomnumber.RandomNumberRepository
import com.example.kmmsample.shared.domain.usecase.login.LoginUseCase
import com.example.kmmsample.shared.domain.usecase.randomnumber.RandomNumberUseCase

class MainActivity : AppCompatActivity() {

    val randomNumberRepository = RandomNumberRepository()
    val randomNumberUseCase = RandomNumberUseCase(randomNumberRepository)

    val api = Api()
    val accountRepository = LoginRepository(api)
    val loginUseCase = LoginUseCase(accountRepository)

    val viewModelFactory = MainViewModel.MainViewModelFactory(randomNumberUseCase, loginUseCase)
    val viewModel: MainViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.bind(layoutInflater.inflate(R.layout.activity_main, null))
        val view = binding.root
        setContentView(view)
        init()
    }


    private fun init() {
        viewModel.randomNumber.observe(this, Observer {
            Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
        })
        viewModel.userProfile.observe(this, Observer {
            Toast.makeText(this, "${it.toString()}", Toast.LENGTH_SHORT).show()
        })
        binding.buttonRandomNumber.setOnClickListener {
            viewModel.executeGetRandomNumber()
        }
        binding.buttonLogin.setOnClickListener {
            viewModel.executeGetUserProfile()
        }
    }
}
