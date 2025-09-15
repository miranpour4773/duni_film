package com.example.dunifilm.Features

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dunifilm.Features.Fragment.Adapter.Special_Genres_Adpter
import com.example.dunifilm.Modle.API_Manager
import com.example.dunifilm.Modle.Movie
import com.example.dunifilm.Modle.keyGenres
import com.example.dunifilm.Modle.keyGenresName
import com.example.dunifilm.Modle.keySendMovieID
import com.example.dunifilm.R
import com.example.dunifilm.databinding.MovieActivtyBinding
import androidx.core.graphics.drawable.toDrawable

class Movie_Activty : AppCompatActivity(), Special_Genres_Adpter.sendData {
    lateinit var listData: List<Movie.Data>
    lateinit var loadingDialog: AlertDialog
    var layoutNumberr = 1
    var genresid: Int = 0
    val apiManager: API_Manager = API_Manager()
    lateinit var binding: MovieActivtyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MovieActivtyBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        createDialog()

        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        binding.toolbar.navigationIcon?.setColorFilter(
            ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP
        )
        genresid = intent.getIntExtra(keyGenres, -1)
        val genresName = intent.getStringExtra(keyGenresName)!!
        supportActionBar?.title = genresName
        if (genresid == 22){
            allmovie(layoutNumberr)
        }else {
            initUi(layoutNumberr)

        }
        binding.next.setOnClickListener {
            if (listData.size == 10) {
                layoutNumberr += 1
                createDialog()
                if (genresid == 22){
                    allmovie(layoutNumberr)
                }else {
                    initUi(layoutNumberr)
                }
                binding.layoutNumber.text = layoutNumberr.toString()
            }
        }

        binding.back.setOnClickListener {
            if (layoutNumberr == 1) {

            } else {
                layoutNumberr -= 1
                createDialog()
                if (genresid == 22){
                    allmovie(layoutNumberr)
                }else {
                    initUi(layoutNumberr)
                }
                binding.layoutNumber.text = layoutNumberr.toString()
            }
        }

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }


    }

    private fun createDialog() {
        val dialogView = layoutInflater.inflate(R.layout.loadinf_dialog , null)
        val builder = AlertDialog.Builder(this)
        builder.setView(dialogView)
        loadingDialog = builder.create()
        loadingDialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        loadingDialog.window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
        loadingDialog.show()

        val scale = resources.displayMetrics.density
        val sizeXY = (250 * scale).toInt()

        loadingDialog.window?.setLayout(sizeXY,sizeXY)

        loadingDialog.setCancelable(false)
    }

    private fun initUi(lyoutNumber: Int) {
        apiManager.getMovies(genresid, layoutNumberr, object : API_Manager.apiCallBack<Movie> {
            override fun onSuccess(data: Movie) {
                listData = data.data
                binding.recyclerView.adapter = Special_Genres_Adpter(listData, this@Movie_Activty)
                binding.recyclerView.layoutManager =
                    GridLayoutManager(this@Movie_Activty, 2, RecyclerView.VERTICAL, false)
                loadingDialog.dismiss()
            }

            override fun onError(errorMessage: String) {
                Toast.makeText(this@Movie_Activty, errorMessage, Toast.LENGTH_SHORT).show()
                loadingDialog.dismiss()

            }
        })
    }

    override fun clickOnMovie(film_id: Int) {
        val intent = Intent(this, Info_Movie_Activity::class.java)
        intent.putExtra(keySendMovieID, film_id)
        startActivity(intent)

    }

    fun allmovie(layoutNumber:Int){
        apiManager.getAllMovie(layoutNumber,object :API_Manager.apiCallBack<Movie>{
            override fun onSuccess(data: Movie) {
                listData = data.data
                binding.recyclerView.adapter = Special_Genres_Adpter(data.data, this@Movie_Activty)
                binding.recyclerView.layoutManager =
                    GridLayoutManager(this@Movie_Activty, 2, RecyclerView.VERTICAL, false)
                loadingDialog.dismiss()
            }

            override fun onError(errorMessage: String) {
                Toast.makeText(this@Movie_Activty, errorMessage, Toast.LENGTH_SHORT).show()
                loadingDialog.dismiss()            }

        })


    }


}