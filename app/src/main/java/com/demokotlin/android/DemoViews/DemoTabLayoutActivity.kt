package com.demokotlin.android
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayout
import com.demokotlin.android.databinding.DemoTablayoutActivityBinding

class DemoTabLayoutActivity : AppCompatActivity() {
    lateinit var binding: DemoTablayoutActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DemoTablayoutActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.MAIN))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        
        binding.tabLayout.setTabTextColors(Color.WHITE, Color.WHITE)
        binding.tabLayout.setSelectedTabIndicatorColor(Color.WHITE)

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Sports"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Movies"))
        binding.tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = DemoTabLayoutAdapter(this, supportFragmentManager, binding.tabLayout.tabCount)
        binding.viewPager.adapter = adapter

        binding.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout))

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.viewPager.currentItem = tab.position
                if (tab.position == 0) {
                    print("Sport")
                } else
                if (tab.position == 1) {
                    print("Movie")
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {
            }
            override fun onTabReselected(tab: TabLayout.Tab) {
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