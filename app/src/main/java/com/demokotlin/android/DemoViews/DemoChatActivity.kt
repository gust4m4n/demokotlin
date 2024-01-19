package com.demokotlin.android
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demokotlin.android.databinding.DemoChatActivityBinding

class DemoChatActivity : AppCompatActivity() {
    lateinit var binding: DemoChatActivityBinding
    var list = ArrayList<DemoMessageModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DemoChatActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.MAIN))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.editMessage.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                val msg = binding.editMessage.text.toString()
                this.sendMessage(msg)
                binding.editMessage.setText("")
            }
            false
        })

        binding.btnSend.setOnClickListener {
            val msg = binding.editMessage.text.toString()
            this.sendMessage(msg)
            binding.editMessage.setText("")
        }

        statusMessage("User connected to channel.")
        sendMessage("Hi...")
        receiveMessage("Hello world!")
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }
    
    fun sendMessage(msg: String) {
        if (msg.length > 0) {
            this.list.add(
                DemoMessageModel(0, msg, false,true)
            )
            this.reload()
        }
    }

    fun receiveMessage(msg: String) {
        if (msg.length > 0) {
            this.list.add(
                DemoMessageModel(0, msg.trim(), false, false)
            )
            this.reload()
        }
    }

    fun statusMessage(msg: String) {
        if (msg.length > 0) {
            this.list.add(
                DemoMessageModel(0, msg, true, false)
            )
            this.reload()
        }
    }

    fun reload() {
        this.binding.recyclerView.layoutManager = LinearLayoutManager(this)
        this.binding.recyclerView.adapter = DemoChatAdapter(this.list)
        this.binding.recyclerView.scrollToPosition(this.list.count() - 1)
    }

}

class DemoChatAdapter(private val list: ArrayList<DemoMessageModel>) : RecyclerView.Adapter<DemoChatAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.demo_chat_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(message: DemoMessageModel) {
            val containerMessageStatus = itemView.findViewById(R.id.containerMessageStatus) as LinearLayout
            val txtMessageStatus = itemView.findViewById(R.id.txtMessageStatus) as TextView
            val containerMessageOutgoing = itemView.findViewById(R.id.containerMessageOutgoing) as LinearLayout
            val txtMessageOutgoing = itemView.findViewById(R.id.txtMessageOutgoing) as TextView
            val containerMessageIncoming = itemView.findViewById(R.id.containerMessageIncoming) as LinearLayout
            val txtMessageIncoming = itemView.findViewById(R.id.txtMessageIncoming) as TextView
            if (message.type == true) {
                containerMessageOutgoing.isVisible = false
                containerMessageIncoming.isVisible = false
                containerMessageStatus.isVisible = true
                txtMessageStatus.text = message.message
            } else {
                containerMessageStatus.isVisible = false
                if (message.direction == true) {
                    containerMessageOutgoing.isVisible = true
                    txtMessageOutgoing.text = message.message
                    containerMessageIncoming.isVisible = false
                } else {
                    containerMessageIncoming.isVisible = true
                    txtMessageIncoming.text = message.message
                    containerMessageOutgoing.isVisible = false
                }
            }
        }
    }
}
