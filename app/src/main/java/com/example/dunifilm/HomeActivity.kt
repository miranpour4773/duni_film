package com.example.dunifilm

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.dunifilm.Modle.API_Manager
import com.example.dunifilm.Modle.Genres
import com.example.dunifilm.databinding.ActivityHomeBinding
import com.google.android.material.chip.Chip

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    val api_manager = API_Manager()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        initUi()
    }

    private fun initUi() {
        api_manager.getGenres(object : API_Manager.apiCallBack<List<Genres>> {
            override fun onSuccess(data: List<Genres>) {
                createChips(data)
            }

            override fun onError(errorMessage: String) {
                Toast.makeText(this@HomeActivity, errorMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }


    private fun createChips(data: List<Genres>) {
        data.forEach {
            val chip = LayoutInflater.from(this)
                .inflate(R.layout.chip_category, binding.chipGroupCategories, false) as Chip
            chip.id = it.id
            chip.text = it.name
            binding.chipGroupCategories.addView(chip)
        }
    }
}