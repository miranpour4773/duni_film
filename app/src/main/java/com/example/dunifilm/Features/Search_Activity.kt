package com.example.dunifilm.Features

import android.os.Bundle
import android.text.Editable
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dunifilm.Modle.API_Manager
import com.example.dunifilm.Modle.Movie
import com.example.dunifilm.Modle.keySearch
import com.example.dunifilm.R
import com.example.dunifilm.databinding.ActivitySearchBinding
import retrofit2.Callback

class Search_Activity : AppCompatActivity() {
    var apiManager = API_Manager()
    lateinit var binding: ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        val query = intent.getStringExtra(keySearch)!!
        binding.edtSearch.setText(query)


        apiManager.searchMovies(query,object :API_Manager.apiCallBack<Movie>{
            override fun onSuccess(data: Movie) {
                
            }

            override fun onError(errorMessage: String) {
                TODO("Not yet implemented")
            }

        })
    }
}