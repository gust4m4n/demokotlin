package com.demokotlin.android
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.PagerAdapter
import com.demokotlin.android.databinding.DemoViewpagerActivityBinding

class DemoViewPagerActivity : AppCompatActivity() {
    lateinit var binding: DemoViewpagerActivityBinding

    val myViews : Array<Int> = arrayOf(R.layout.demo_viewpager_layout_one,
        R.layout.demo_viewpager_layout_two,
        R.layout.demo_viewpager_layout_three)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DemoViewpagerActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.MAIN))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.myViewPager.adapter = MyViewPagerAdapter(myViews, this@DemoViewPagerActivity)
        binding.dotsIndicator.setViewPager(binding.myViewPager)
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

class MyViewPagerAdapter (private val theViews: Array<Int>,
                 private val theContext: Context
) : PagerAdapter()
{
    override fun isViewFromObject(view: View,
                                  `object`: Any): Boolean
    {
        return view === `object`
    }

    // We get the number of pages for ViewPager from the size of the array.
    override fun getCount(): Int
    {
        return theViews.size
    }

    // Instantiate a new item from the array.
    override fun instantiateItem(container: ViewGroup,
                                 position: Int): Any
    {
        val thisView = theViews[position]
        val inflater = LayoutInflater.from(theContext)

        val layout = inflater.inflate(thisView, container,
            false) as ViewGroup

        container.addView(layout)
        return layout
    }

    override fun destroyItem(container: ViewGroup,
                             position: Int,
                             view: Any)
    {
        container.removeView(view as View)
    }
}