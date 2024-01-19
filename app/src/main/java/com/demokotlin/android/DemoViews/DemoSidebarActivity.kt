package com.demokotlin.android
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.*
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.navigation.NavigationView
import com.demokotlin.android.DemoViewModels.DemoContactListVM
import org.json.JSONObject
import com.demokotlin.android.databinding.DemoSidebarActivityBinding

class DemoSidebarActivity : AppCompatActivity() {
    lateinit var binding: DemoSidebarActivityBinding
    private lateinit var mToggle : ActionBarDrawerToggle
    val contactListVM = DemoContactListVM()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DemoSidebarActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.MAIN))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        mToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(mToggle)
        mToggle.syncState()

        val headerView : View = navView.getHeaderView(0)
        val ivAvatar : ImageView = headerView.findViewById(R.id.ivAvatar)
        ivAvatar.clipToOutline = true

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.edit_profile -> {
                    val intent = Intent(this, DemoSettingsActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    startActivity(intent)
                    true
                } else -> {
                    true
                }
            }
        }

        reload()
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        return mToggle.onOptionsItemSelected(item)
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
    }

}

class DemoChatListCustomAdapter(private val list: ArrayList<DemoContactModel>) : RecyclerView.Adapter<DemoChatListCustomAdapter.ViewHolder>() {

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
