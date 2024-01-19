package com.demokotlin.android

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity

fun setAutoDismissKeyboard(activity: AppCompatActivity, container: View) {
    container.setOnTouchListener { v, _ ->
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(v.windowToken, 0)
        false
    }
}

