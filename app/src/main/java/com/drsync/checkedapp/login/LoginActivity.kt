package com.drsync.checkedapp.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.drsync.checkedapp.MainActivity
import com.drsync.checkedapp.R
import com.drsync.checkedapp.databinding.ActivityLoginBinding
import com.drsync.checkedapp.databinding.ActivityMainBinding
import com.drsync.core.data.Resource
import com.drsync.core.data.remote.request.LoginRequest
import com.drsync.core.data.remote.request.RegisterRequest
import com.drsync.core.data.remote.response.ServerResponse
import com.drsync.core.data.remote.response.Token
import com.drsync.core.util.ConstantFunction
import com.drsync.core.util.ConstantFunction.showSnackbar
import com.drsync.core.util.ConstantFunction.viewError
import com.drsync.core.util.ConstantVariable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkToken()
        binding.btnLogin.setOnClickListener {
            checkInput()
        }
    }

    private fun checkInput() {
        binding.apply {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()

            val loginRequest = LoginRequest(
                username, password
            )
            if(validateInput(loginRequest)){
                loginUser(loginRequest)
            }
        }
    }

    private fun loginUser(loginRequest: LoginRequest) {
        viewModel.loginUser(loginRequest).observe(this@LoginActivity){ result ->
            binding.apply {
                when(result){
                    is Resource.Loading -> {
                        progressBar.isVisible = true
                    }
                    is Resource.Success -> {
                        progressBar.isVisible = false
                        saveToken(result.data)
                        checkToken()
                    }
                    is Resource.Error -> {
                        progressBar.isVisible = false
                        root.showSnackbar(result.error)
                        Log.d(ConstantVariable.TAG, "registerUser: ${result.error}")
                    }
                }
            }
        }
    }

    private fun checkToken() {
        viewModel.getToken().observe(this@LoginActivity){ token ->
            if(token.token.isNotEmpty()){
                Intent(this, MainActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(it)
                }
            }
        }
    }

    private fun saveToken(data: ServerResponse) {
        viewModel.saveToken(data.data as Token)
    }

    private fun validateInput(registerRequest: LoginRequest): Boolean {
        binding.apply {
            if(registerRequest.username.isEmpty()){
                return ilUsername.viewError(this@LoginActivity)
            }
            if(registerRequest.password.isEmpty()){
                return ilPassword.viewError(this@LoginActivity)
            }
        }
        return true
    }
}