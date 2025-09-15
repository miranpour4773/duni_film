package com.example.dunifilm.Features

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.dunifilm.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)



        binding.viewPager2.adapter = ViewPager_Adapter(this)
        binding.viewPager2.isUserInputEnabled = false

        binding.home.setOnClickListener {
            binding.viewPager2.currentItem = 0
        }
        binding.watchList.setOnClickListener {
            if (binding.viewPager2.adapter != null && binding.viewPager2.adapter!!.itemCount > 1) {
                binding.viewPager2.currentItem = 1
            }
        }

    }


}