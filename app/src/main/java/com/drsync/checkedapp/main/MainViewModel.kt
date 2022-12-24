package com.drsync.checkedapp.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.drsync.core.data.ChekedAppRepository
import com.drsync.core.data.remote.request.CheckListRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ChekedAppRepository
) : ViewModel() {

    fun getToken() = repository.getToken().asLiveData()

    fun insertChecklist(header: String, request: CheckListRequest) =
        repository.insertCheckList(header, request).asLiveData()

    fun getCheckList(header: String) =
        repository.getCheckList(header).asLiveData()
}