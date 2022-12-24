package com.drsync.checkedapp.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.drsync.core.data.ChekedAppRepository
import com.drsync.core.data.remote.request.RegisterRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: ChekedAppRepository
) : ViewModel() {
    fun registerUser(request: RegisterRequest) = repository.registerUser(request).asLiveData()
}