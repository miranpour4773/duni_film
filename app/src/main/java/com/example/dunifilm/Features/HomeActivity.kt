package com.example.dunifilm.Features

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.dunifilm.Modle.API_Manager
import com.example.dunifilm.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    val api_manager = API_Manager()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)


        binding.viewPager2.adapter = ViewPager_Adapter(this)
        binding.viewPager2.isUserInputEnabled = false
    }


}