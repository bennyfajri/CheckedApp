package com.drsync.core.util

import android.content.Context
import android.view.View
import com.drsync.core.R
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

object ConstantFunction {
    fun View.showSnackbar(message: String) = Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()

    fun setCustomMessageError(inputLayout: TextInputLayout, string: String): Boolean {
        inputLayout.isErrorEnabled = true
        inputLayout.error = string
        inputLayout.requestFocus()
        return false
    }

    fun TextInputLayout.viewError(context: Context): Boolean {
        this.isErrorEnabled = true
        this.error = context.getString(R.string.must_not_empty)
        this.requestFocus()
        return false
    }
}