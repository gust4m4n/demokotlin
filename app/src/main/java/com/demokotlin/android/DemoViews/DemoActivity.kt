package com.demokotlin.android
import DemoBottomSheetDialog
import DemoCustomDialog
import android.R.attr
import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NO_ANIMATION
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.google.zxing.integration.android.IntentIntegrator
import androidx.databinding.DataBindingUtil
import com.demokotlin.android.databinding.DemoActivityBinding
import java.text.SimpleDateFormat
import java.util.*
import android.R.attr.data

import com.google.zxing.integration.android.IntentResult

class DemoActivity : AppCompatActivity() {
    lateinit var binding: DemoActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DemoActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.MAIN))
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        binding.btnPrimary.setOnClickListener {
            Toast.makeText(applicationContext,"Button Primary clicked.", Toast.LENGTH_SHORT).show()
        }

        binding.btnSecondary.setOnClickListener {
            Toast.makeText(applicationContext,"Button Secondary clicked.", Toast.LENGTH_SHORT).show()
        }

        binding.btnGray.setOnClickListener {
            Toast.makeText(applicationContext,"Button Gray clicked.", Toast.LENGTH_SHORT).show()
        }

        binding.btnDisabled.setOnClickListener {
            Toast.makeText(applicationContext,"Button Disabled clicked.", Toast.LENGTH_SHORT).show()
        }

        binding.btnToast.setOnClickListener {
            Toast.makeText(applicationContext,"btnToast clicked.", Toast.LENGTH_SHORT).show()
        }

        binding.btnAlertDialog.setOnClickListener {

            val builder = AlertDialog.Builder(this)
            builder.setTitle("AlertDialog title")
            builder.setMessage("AlertDialog message")
            builder.setPositiveButton(android.R.string.yes) { _, _ ->
                Toast.makeText(applicationContext,
                    android.R.string.yes, Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton(android.R.string.no) { _, _ ->
                Toast.makeText(applicationContext,
                    android.R.string.no, Toast.LENGTH_SHORT).show()
            }
            builder.show()

        }

        binding.btnImage.setOnClickListener {
            val intent = Intent(this, DemoImageActivity::class.java)
            intent.addFlags(FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        binding.btnGlide.setOnClickListener {
            val intent = Intent(this, DemoGlideActivity::class.java)
            intent.addFlags(FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        binding.btnListView.setOnClickListener {
            val intent = Intent(this, DemoListViewActivity::class.java)
            intent.addFlags(FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        binding.btnWebView.setOnClickListener {
            val intent = Intent(this, DemoWebViewActivity::class.java)
            intent.addFlags(FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        binding.btnWebViewString.setOnClickListener {
            val intent = Intent(this, DemoWebViewStringActivity::class.java)
            intent.addFlags(FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        binding.btnWebViewAsset.setOnClickListener {
            val intent = Intent(this, DemoWebViewAssetActivity::class.java)
            intent.addFlags(FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        binding.btnGridView.setOnClickListener {
            val intent = Intent(this, DemoGridActivity::class.java)
            intent.addFlags(FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        binding.btnRecyclerView.setOnClickListener {
            val intent = Intent(this, DemoRecyclerViewActivity::class.java)
            intent.addFlags(FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        binding.btnCustomDialog.setOnClickListener {
            val dialog = DemoCustomDialog(this)
            dialog.showWith ("Hello...", {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }, {
                Toast.makeText(this, "Cancelled.", Toast.LENGTH_SHORT).show()
            })
        }

        binding.btnBottomSheet.setOnClickListener {
            val dialog = DemoBottomSheetDialog(this)
            dialog.showWith({
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }, {
                Toast.makeText(this, "Cancelled.", Toast.LENGTH_SHORT).show()
            })
        }

        binding.btnBottomNavigation.setOnClickListener {
            val intent = Intent(this, DemoBottomNavigationActivity::class.java)
            intent.addFlags(FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        binding.btnSettings.setOnClickListener {
            val intent = Intent(this, DemoSettingsActivity::class.java)
            intent.addFlags(FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        binding.btnShare.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "This is my text to share.")
                type = "text/plain"
            }
            startActivity(sendIntent)
        }

        binding.btnTabLayout.setOnClickListener {
            val intent = Intent(this, DemoTabLayoutActivity::class.java)
            intent.addFlags(FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        binding.btnVideo.setOnClickListener {
            val intent = Intent(this, DemoVideoActivity::class.java)
            intent.addFlags(FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        binding.btnSnackBar.setOnClickListener {

            /*
            val snackBar = Snackbar.make(
                it, "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                Snackbar.LENGTH_LONG
            ).setAction("OK", View.OnClickListener {
                println("[SNACKBAR] OnClick.")
            })
            snackBar.setActionTextColor(Color.BLACK)
            val snackBarView = snackBar.view
            val textView = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
            textView.maxLines = 32
            snackBarView.setBackgroundColor(Color.WHITE)
            textView.setTextColor(Color.BLACK)
            snackBar.show()
            */
        }

        val imagePickerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { it ->
            if (it.resultCode == Activity.RESULT_OK) {
                val data: Intent? = it.data
                binding.imageView.setImageURI(data?.data)
            }
        }

        binding.btnImagePicker.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.addFlags(FLAG_ACTIVITY_NO_ANIMATION)
            intent.type = "image/*"
            imagePickerLauncher.launch(intent)
        }

        val takePhotoLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                binding.imageView.setImageBitmap(data?.extras?.get("data") as Bitmap)
            }
        }

        binding.btnTakePhoto.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.addFlags(FLAG_ACTIVITY_NO_ANIMATION)
            takePhotoLauncher.launch(intent)
        }

        binding.btnGenerateQR.setOnClickListener {
            val intent = Intent(this, DemoGenerateQRActivity::class.java)
            intent.addFlags(FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        val takePhotoLauncher2 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { it ->

            if (it.resultCode == Activity.RESULT_OK) {

                val bundle = it.data?.extras
                var scanResult = bundle!!.getString("SCAN_RESULT")
                Toast.makeText(this, scanResult, Toast.LENGTH_LONG).show()
            }

        }

        binding.btnScanQR.setOnClickListener {

            val intentIntegrator = IntentIntegrator(this@DemoActivity)
            intentIntegrator.setBeepEnabled(true)
            intentIntegrator.setCameraId(0)
            intentIntegrator.setPrompt("SCAN QR")
            intentIntegrator.setOrientationLocked(true)
            val intent = intentIntegrator.createScanIntent()
            takePhotoLauncher2.launch(intent)


        }



        binding.btnViewPager.setOnClickListener {
            val intent = Intent(this, DemoViewPagerActivity::class.java)
            intent.addFlags(FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        binding.btnTimePicker.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                println("time: ${SimpleDateFormat("HH:mm").format(cal.time)}")
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }

        binding.btnDatePicker.setOnClickListener {

            val c = Calendar.getInstance()
            val currYear = c.get(Calendar.YEAR)
            val currMonth = c.get(Calendar.MONTH)
            val currDay = c.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                println("date: $dayOfMonth ${monthOfYear+1} $year")
            }, currYear, currMonth, currDay)
            dpd.show()

        }


        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, DemoLoginActivity::class.java)
            intent.addFlags(FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, DemoRegisterActivity::class.java)
            intent.addFlags(FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        binding.btnChatList.setOnClickListener {
            val intent = Intent(this, DemoSidebarActivity::class.java)
            intent.addFlags(FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        binding.btnChat.setOnClickListener {
            val intent = Intent(this, DemoChatActivity::class.java)
            intent.addFlags(FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

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