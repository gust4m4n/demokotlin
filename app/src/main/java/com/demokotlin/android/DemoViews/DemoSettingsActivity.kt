package com.demokotlin.android
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.demokotlin.android.databinding.DemoSettingsActivityBinding

class DemoSettingsActivity : AppCompatActivity() {
    lateinit var binding: DemoSettingsActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DemoSettingsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.MAIN))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                println("[seekBar] onProgressChanged: $i")
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                println("[seekBar] onStartTrackingTouch")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                println("[seekBar] onStopTrackingTouch")
            }
        })

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