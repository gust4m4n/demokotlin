package com.demokotlin.android
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.demokotlin.android.databinding.DemoListviewActivityBinding
import org.json.JSONObject
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.view.*
import com.demokotlin.android.DemoViewModels.DemoContactListVM

class DemoListViewActivity : AppCompatActivity() {
    lateinit var binding: DemoListviewActivityBinding
    private val contactListVM = DemoContactListVM()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DemoListviewActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.MAIN))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.listView.onItemClickListener = object : AdapterView.OnItemClickListener {

            override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val item = binding.listView.getItemAtPosition(position) as DemoContactModel
                val intent = Intent(this@DemoListViewActivity, DemoDetailActivity::class.java)
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
        this.binding.listView.isVisible = false
        this.binding.progressBar.isVisible = true
        this.contactListVM.request{ statusCode: Int, errMsg: String, json: JSONObject ->
            this.binding.listView.isVisible = true
            this.binding.progressBar.isVisible = false
            if (statusCode == 200) {
                val adapter = DemoListViewAdapter(this, this.contactListVM.list)
                binding.listView.setAdapter(adapter)
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

class DemoListViewAdapter : BaseAdapter {
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

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val contact = this.list[position]
        val viewHolder: ViewHolder
        val rowView: View?
        if (convertView == null) {
            val layoutInflater = LayoutInflater.from(context)
            rowView = layoutInflater.inflate(R.layout.demo_listview_item, parent, false)
            viewHolder = ViewHolder(rowView)
            rowView.tag = viewHolder
        } else {
            rowView = convertView
            viewHolder = rowView.tag as ViewHolder
        }
        viewHolder.ivAvatar.clipToOutline = true
        Glide.with(context!!).load(contact.avatar).into(viewHolder.ivAvatar)
        viewHolder.txtFullname.text = contact.fullname
        viewHolder.txtPhone.text = contact.phone
        return rowView
    }

    private class ViewHolder(view: View?) {
        val ivAvatar = view?.findViewById(R.id.ivAvatar) as ImageView
        val txtFullname = view?.findViewById(R.id.txtFullname) as TextView
        val txtPhone = view?.findViewById(R.id.txtPhone) as TextView
    }
}