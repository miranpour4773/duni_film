package com.example.dunifilm.Features

import android.app.Dialog
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dunifilm.Features.Fragment.Adapter.Recycler_Actors
import com.example.dunifilm.Modle.API_Manager
import com.example.dunifilm.Modle.Movie_Info
import com.example.dunifilm.Modle.keySendMovieID
import com.example.dunifilm.R
import com.example.dunifilm.databinding.ActivityInfoMovieBinding
import com.google.android.material.chip.Chip
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Info_Movie_Activity : AppCompatActivity() {
    val apiManager = API_Manager()
    lateinit var loadingDialog: AlertDialog
    lateinit var binding: ActivityInfoMovieBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoMovieBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""
        binding.toolbar.navigationIcon?.setColorFilter(
            ContextCompat.getColor(this, R.color.white),
            PorterDuff.Mode.SRC_ATOP
        )

        createDialog()


        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }


        val movieId = intent.getIntExtra(keySendMovieID, 1)



        apiManager.apiService.getMovieInfo(movieId).enqueue(object : Callback<Movie_Info> {
            override fun onResponse(call: Call<Movie_Info>, response: Response<Movie_Info>) {
                initUi(response.body()!!)
                loadingDialog.dismiss()
            }

            override fun onFailure(call: Call<Movie_Info>, t: Throwable) {
                Toast.makeText(this@Info_Movie_Activity, t.message.toString(), Toast.LENGTH_SHORT)
                    .show()
                loadingDialog.dismiss()
            }

        })


    }

    private fun createDialog() {
        val dialogView = layoutInflater.inflate(R.layout.loadinf_dialog, null)
        val builder = AlertDialog.Builder(this)
        builder.setView(dialogView)

        loadingDialog = builder.create()
        loadingDialog.window?.setBackgroundDrawableResource(R.color.shafaf)
        loadingDialog.show()

        val scale = resources.displayMetrics.density
        val sizePx = (250 * scale).toInt()
        loadingDialog.window?.setLayout(sizePx, sizePx)

        loadingDialog.setCancelable(false)
    }

    fun initUi(movieInfo: Movie_Info) {
        binding.txtName.text = movieInfo.title
        binding.txtYear.text = movieInfo.year
        binding.rating.text = movieInfo.imdbRating

        val review = ((movieInfo.imdbVotes).replace(",", "").toInt()) / 1000
        binding.txtReview.text = review.toString() + "K"

        binding.txtPlot.text = movieInfo.plot

        binding.txtCountry.text = movieInfo.country
        binding.txtDateRelease.text = movieInfo.released

        val time = movieInfo.runtime.replace("min", "").trim().toInt()
        val hour = time / 60
        val min = time % 60

        binding.txtHour.text = "$hour hr $min min"
        Glide.with(binding.root)
            .load(movieInfo.poster)
            .into(binding.imgPoster)


        movieInfo.genres?.forEach {
            val chip = LayoutInflater.from(this)
                .inflate(R.layout.chip_genres_of_movie, binding.chipGroup, false) as Chip
            chip.text = it
            binding.chipGroup.addView(chip)
        }

        binding.recyclerView.adapter = Recycler_Actors(movieInfo.images)
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

    }

}