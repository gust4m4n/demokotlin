package com.demokotlin.android
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.demokotlin.android.databinding.DemoWebViewActivityBinding

class DemoWebViewActivity : AppCompatActivity() {
    lateinit var binding: DemoWebViewActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DemoWebViewActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.MAIN))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.webView.loadUrl("https://kotlinlang.org/")
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