package com.drsync.checkedapp.register

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.drsync.checkedapp.R
import com.drsync.checkedapp.databinding.ActivityRegisterBinding
import com.drsync.core.data.Resource
import com.drsync.core.data.remote.request.RegisterRequest
import com.drsync.core.util.ConstantFunction.setCustomMessageError
import com.drsync.core.util.ConstantFunction.showSnackbar
import com.drsync.core.util.ConstantFunction.viewError
import com.drsync.core.util.ConstantVariable.TAG
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            checkInput()
        }
    }

    private fun checkInput() {
        binding.apply {
            val username = etUsername.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            val registerRequest = RegisterRequest(
                email, password, username
            )
            if(validateInput(registerRequest)){
                registerUser(registerRequest)
            }
        }
    }

    private fun registerUser(registerRequest: RegisterRequest) {
        viewModel.registerUser(registerRequest).observe(this@RegisterActivity){ result ->
            binding.apply {
                when(result){
                    is Resource.Loading -> {
                        progressBar.isVisible = true
                    }
                    is Resource.Success -> {
                        progressBar.isVisible = false
                        finish()
                    }
                    is Resource.Error -> {
                        progressBar.isVisible = false
                        root.showSnackbar(result.error)
                        Log.d(TAG, "registerUser: ${result.error}")
                    }
                }
            }
        }
    }

    private fun validateInput(registerRequest: RegisterRequest): Boolean {
        binding.apply {
            if(registerRequest.email.isEmpty()){
                return ilEmail.viewError(this@RegisterActivity)
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(registerRequest.email).matches()) {
                return setCustomMessageError(ilEmail, getString(R.string.not_email_input))
            }
            if(registerRequest.username.isEmpty()){
                return ilUsername.viewError(this@RegisterActivity)
            }
            if(registerRequest.password.isEmpty()){
                return ilPassword.viewError(this@RegisterActivity)
            }
        }
        return true
    }
}