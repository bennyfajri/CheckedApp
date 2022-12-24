package com.drsync.checkedapp.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.drsync.core.data.ChekedAppRepository
import com.drsync.core.data.remote.request.LoginRequest
import com.drsync.core.data.remote.response.Token
import com.drsync.core.data.remote.response.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: ChekedAppRepository
) : ViewModel() {
    fun loginUser(request: LoginRequest) = repository.loginUser(request).asLiveData()

    fun saveToken(token: Token) = viewModelScope.launch {
        repository.saveToken(token)
    }

    fun getToken() = repository.getToken().asLiveData()
}