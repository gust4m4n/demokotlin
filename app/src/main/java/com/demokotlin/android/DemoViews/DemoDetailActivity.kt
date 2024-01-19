package com.demokotlin.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.demokotlin.android.databinding.DemoDetailActivityBinding

class DemoDetailActivity : AppCompatActivity() {
    lateinit var binding: DemoDetailActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DemoDetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.MAIN))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val avatar = intent.getStringExtra("avatar")
        Glide.with(this).load(avatar).into(binding.imageView)

        val fullname = intent.getStringExtra("fullname")
        binding.textView.text = fullname

        val phone = intent.getStringExtra("phone")
        binding.webView.getSettings().setJavaScriptEnabled(true)
        binding.webView.loadData("<body style='margin:0;padding:0;'>$phone", "text/html; charset=utf-8", "UTF-8")
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