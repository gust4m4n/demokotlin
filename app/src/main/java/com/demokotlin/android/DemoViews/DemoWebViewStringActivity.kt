package com.demokotlin.android
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.demokotlin.android.databinding.DemoWebViewStringActivityBinding

class DemoWebViewStringActivity : AppCompatActivity() {
    lateinit var binding: DemoWebViewStringActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DemoWebViewStringActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.MAIN))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val data = "<!DOCTYPE html>\n" +
                "<html>\n" +
                " <body>\n" +
                "  <h1 style=\"text-align:center;\">Hello World</h1>\n" +
                " </body>\n" +
                "</html>"
        binding.webView.getSettings().setJavaScriptEnabled(true)
        binding.webView.loadData(data, "text/html; charset=utf-8", "UTF-8")
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