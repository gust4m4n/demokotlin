package com.demokotlin.android

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.*
import com.demokotlin.android.databinding.DemoLoginActivityBinding

class DemoLoginActivity : AppCompatActivity() {
    lateinit var binding: DemoLoginActivityBinding
    private val RC_SIGN_IN = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DemoLoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.WHITE)
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        binding.btnLogin.setOnClickListener {
            this.binding.progressBar.isVisible = true
            this.binding.progressBar.isVisible = false
        }

        binding.btnForgotPassword.setOnClickListener {
        }

        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, DemoRegisterActivity::class.java)
            startActivity(intent)
        }

        setAutoDismissKeyboard(this, binding.container)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }


}