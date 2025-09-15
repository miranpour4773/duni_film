package com.example.dunifilm.Features

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.dunifilm.Features.Fragment.Fragment_Home
import com.example.dunifilm.Features.Fragment.WatchListFrgment
import com.example.dunifilm.databinding.HomeFragmentBinding

class ViewPager_Adapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> Fragment_Home()
            1 -> WatchListFrgment()

            else -> Fragment()
        }
    }
}