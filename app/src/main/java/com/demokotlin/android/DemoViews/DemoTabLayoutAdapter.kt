package com.demokotlin.android

import DemoTabLayoutMoviesFragment
import DemoTabLayoutSportsFragment
import android.content.Context;
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class DemoTabLayoutAdapter(private val myContext: Context, fm: FragmentManager, internal var totalTabs: Int) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return DemoTabLayoutSportsFragment()
            }
            1 -> {
                return DemoTabLayoutMoviesFragment()
            } else -> {
                return DemoTabLayoutMoviesFragment()
            }
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }
}