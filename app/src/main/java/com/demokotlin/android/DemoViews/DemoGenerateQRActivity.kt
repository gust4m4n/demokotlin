package com.demokotlin.android
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.demokotlin.android.databinding.DemoImageActivityBinding

class DemoGenerateQRActivity : AppCompatActivity() {
    lateinit var binding: DemoImageActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DemoImageActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.MAIN))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val bitmap = generateQRCode("Hello World!")
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        binding.imageView.setImageBitmap(bitmap)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }

    private fun generateQRCode(text: String): Bitmap {
        val width = 500
        val height = 500
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val codeWriter = MultiFormatWriter()
        try {
            val bitMatrix = codeWriter.encode(text, BarcodeFormat.QR_CODE, width, height)
            for (x in 0 until width) {
                for (y in 0 until height) {
                    bitmap.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                }
            }
        } catch (e: WriterException) {
        }
        return bitmap
    }


}