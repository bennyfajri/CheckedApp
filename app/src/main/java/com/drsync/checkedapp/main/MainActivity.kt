package com.drsync.checkedapp.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.drsync.checkedapp.R
import com.drsync.checkedapp.databinding.ActivityMainBinding
import com.drsync.core.data.Resource
import com.drsync.core.util.ConstantVariable.tokenBearer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        getToken()
        getCheckList()
    }

    private fun getToken() {
        viewModel.getToken().observe(this@MainActivity) {
            viewModel.getCheckList(it.tokenBearer).observe(this@MainActivity) { result ->
                binding.apply {
                    when (result) {
                        is Resource.Loading -> {
                            progressBar.isVisible = true
                        }
                        is Resource.Success -> {
                            progressBar.isVisible = false
                        }
                        is Resource.Error -> {
                            progressBar.isVisible = false
                        }
                    }
                }

            }
        }
    }

    private fun getCheckList() {

    }
}