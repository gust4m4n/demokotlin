package com.demokotlin.android
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.*
import com.bumptech.glide.Glide
import com.demokotlin.android.DemoViewModels.DemoContactListVM
import com.demokotlin.android.databinding.DemoGridActivityBinding
import okhttp3.*
import org.json.JSONObject

class DemoGridActivity : AppCompatActivity() {
    lateinit var binding: DemoGridActivityBinding
    private val contactListVM = DemoContactListVM()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DemoGridActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.MAIN))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.gridView.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val item = binding.gridView.getItemAtPosition(position) as DemoContactModel
                val intent = Intent(this@DemoGridActivity, DemoDetailActivity::class.java)
                intent.putExtra("avatar", item.avatar)
                intent.putExtra("fullname", item.fullname)
                intent.putExtra("phone", item.phone)
                startActivity(intent)

            }
        }

        reload()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }

    fun reload() {
        this.binding.gridView.isVisible = false
        this.binding.progressBar.isVisible = true
        this.contactListVM.request{ statusCode: Int, errMsg: String, json: JSONObject ->
            this.binding.gridView.isVisible = true
            this.binding.progressBar.isVisible = false
            if (statusCode == 200) {
                val adapter = GridAdapter(this, this.contactListVM.list)
                binding.gridView.setAdapter(adapter)

                Toast.makeText(
                    applicationContext, json.getString("message"), Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    applicationContext, errMsg, Toast.LENGTH_SHORT
                ).show()
            }
        }
    }



}

class GridAdapter : BaseAdapter {
    var context: Context? = null
    var list = ArrayList<DemoContactModel>()

    constructor(context: Context, list: ArrayList<DemoContactModel>) : super() {
        this.context = context
        this.list = list
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val contact = this.list[position]
        val itemView = LayoutInflater.from(parent!!.context).inflate(R.layout.demo_gridview_item, parent, false)
        val imageView = itemView.findViewById(R.id.imageView) as ImageView
        Glide.with(context!!).load(contact.avatar).into(imageView)

        val displayMetrics = DisplayMetrics()
        val windowsManager = context!!.getSystemService(AppCompatActivity.WINDOW_SERVICE) as WindowManager
        windowsManager.defaultDisplay.getMetrics(displayMetrics)
        val deviceWidth = displayMetrics.widthPixels
        itemView.setLayoutParams(AbsListView.LayoutParams(deviceWidth/3, deviceWidth/3))

        return itemView
    }
}
