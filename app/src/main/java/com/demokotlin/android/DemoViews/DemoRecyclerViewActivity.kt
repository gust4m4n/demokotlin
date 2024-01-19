package com.demokotlin.android
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demokotlin.android.DemoViewModels.DemoContactListVM
import com.bumptech.glide.Glide
import org.json.JSONObject
import com.demokotlin.android.databinding.DemoRecyclerViewActivityBinding

class DemoRecyclerViewActivity : AppCompatActivity() {
    lateinit var binding: DemoRecyclerViewActivityBinding
    private val contactListVM = DemoContactListVM()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DemoRecyclerViewActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.MAIN))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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
        this.binding.recyclerView.isVisible = false
        this.binding.progressBar.isVisible = true
        this.contactListVM.request{ statusCode: Int, errMsg: String, json: JSONObject ->
            this.binding.recyclerView.isVisible = true
            this.binding.progressBar.isVisible = false
            if (statusCode == 200) {
                this.binding.recyclerView.layoutManager = LinearLayoutManager(this)
                this.binding.recyclerView.adapter = DemoContactListCustomAdapter(this.contactListVM.list)
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

class DemoContactListCustomAdapter(private val list: ArrayList<DemoContactModel>) : RecyclerView.Adapter<DemoContactListCustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.demo_recycler_view_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, DemoDetailActivity::class.java)
            intent.putExtra("avatar", item.avatar)
            intent.putExtra("fullname", item.fullname)
            intent.putExtra("phone", item.phone)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(chat: DemoContactModel) {
            val ivAvatar = itemView.findViewById(R.id.ivAvatar) as ImageView
            val txtFullname = itemView.findViewById(R.id.txtFullname) as TextView
            val txtPhone = itemView.findViewById(R.id.txtPhone) as TextView
            ivAvatar.clipToOutline = true
            Glide.with(itemView.context).load(chat.avatar).into(ivAvatar)
            txtFullname.text = chat.fullname
            txtPhone.text = chat.phone
        }
    }

}
