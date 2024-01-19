package com.demokotlin.android
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.*
import com.demokotlin.android.databinding.DemoVideoActivityBinding

class DemoVideoActivity : AppCompatActivity() {
    lateinit var binding: DemoVideoActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DemoVideoActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.MAIN))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.videoView.setVideoPath("http://www.ebookfrenzy.com/android_book/movie.mp4")
        binding.videoViewContainer.clipToOutline = true
        binding.videoView.setOnPreparedListener { it ->
            it.isLooping = true
            val videoRatio = it.videoWidth / it.videoHeight.toFloat()
            println("[VIDEO] videoWidth: ${it.videoWidth} videoHeight: ${it.videoHeight} ratio: {$videoRatio}")
            if (it.videoWidth > it.videoHeight) { // landscape
                val scale = it.videoHeight / binding.videoView.height.toFloat()
                println("[VIDEO] landscape scale: $scale")
                binding.videoView.scaleX = scale
                binding.videoView.scaleY = scale

            } else {
                val scale = it.videoWidth / binding.videoView.width.toFloat()
                println("[VIDEO] portrait scale: $scale")
                binding.videoView.scaleX = scale
                binding.videoView.scaleY = scale
            }
            binding.placeholder.isVisible = false
        }

        binding.videoView.start()
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