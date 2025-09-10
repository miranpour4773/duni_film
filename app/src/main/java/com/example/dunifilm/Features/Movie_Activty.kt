package com.example.dunifilm.Features

import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dunifilm.Features.Fragment.Adapter.Special_Genres_Adpter
import com.example.dunifilm.Modle.API_Manager
import com.example.dunifilm.Modle.Movie
import com.example.dunifilm.Modle.keyGenres
import com.example.dunifilm.Modle.keyGenresName
import com.example.dunifilm.R
import com.example.dunifilm.databinding.ActivityMovieActivtyBinding

class Movie_Activty : AppCompatActivity() {
    lateinit var listData: List<Movie.Data>
    var layoutNumberr = 1
    var genresid :Int =0
    val apiManager: API_Manager = API_Manager()
    lateinit var binding: ActivityMovieActivtyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieActivtyBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        binding.toolbar.navigationIcon?.setColorFilter(
            ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP
        )
        genresid = intent.getIntExtra(keyGenres ,-1)
        val genresName = intent.getStringExtra(keyGenresName)!!
        Log.v("testiiii",genresName)
        supportActionBar?.title = genresName

        initUi(layoutNumberr)


        binding.next.setOnClickListener {
            if (listData.size == 10){
                layoutNumberr += 1
                initUi(layoutNumberr)
                binding.layoutNumber.text = layoutNumberr.toString()
            }
        }

        binding.back.setOnClickListener {
            if (layoutNumberr == 1){

            }else{
                layoutNumberr-=1
                initUi(layoutNumberr)
                binding.layoutNumber.text = layoutNumberr.toString()
            }
        }

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }


    }

    private fun initUi(lyoutNumber:Int) {
        apiManager.getMovies(genresid , layoutNumberr,object : API_Manager.apiCallBack<Movie>{
            override fun onSuccess(data: Movie) {
                listData = data.data
                binding.recyclerView.adapter = Special_Genres_Adpter(data.data)
                binding.recyclerView.layoutManager = GridLayoutManager(this@Movie_Activty ,2 , RecyclerView.VERTICAL,false)
            }

            override fun onError(errorMessage: String) {
                Toast.makeText(this@Movie_Activty, errorMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }

}