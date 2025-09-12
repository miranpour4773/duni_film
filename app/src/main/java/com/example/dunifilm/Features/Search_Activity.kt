package com.example.dunifilm.Features

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dunifilm.Features.Fragment.Adapter.Special_Genres_Adpter
import com.example.dunifilm.Modle.API_Manager
import com.example.dunifilm.Modle.Movie
import com.example.dunifilm.Modle.keySendMovieID
import com.example.dunifilm.R
import com.example.dunifilm.databinding.ActivitySearchBinding

class Search_Activity : AppCompatActivity(), Special_Genres_Adpter.sendData {
    var apiManager = API_Manager()
    lateinit var loadingDialog: AlertDialog
    lateinit var txt_search: String
    var layoutNumber: Int = 1
    lateinit var binding: ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        binding.toolbar.navigationIcon?.setColorFilter(
            ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP
        )


        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        // edt text in searchbar
        val searchText =
            binding.search.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
        searchText.setTextColor(Color.WHITE)
        binding.search.isFocusable = true
        binding.search.isIconified = false
        binding.search.requestFocusFromTouch()


        // icon search in search bar
        val ic_search =
            binding.search.findViewById<ImageView>(androidx.appcompat.R.id.search_mag_icon)
        ic_search.setColorFilter(Color.WHITE)

        //icon clear in search bar
        val ic_clear =
            binding.search.findViewById<ImageView>(androidx.appcompat.R.id.search_close_btn)
        ic_clear.setColorFilter(Color.WHITE)

        // done
        binding.search.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    if (query.length > 0) {
                        serch(query)
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        val dialogView = layoutInflater.inflate(R.layout.loadinf_dialog, null)
        val builder = AlertDialog.Builder(this)
        builder.setView(dialogView)
        loadingDialog = builder.create()

        loadingDialog.window?.setBackgroundDrawableResource(R.color.shafaf)
        loadingDialog.setCancelable(false)
        val scale = resources.displayMetrics.density
        val sizeXY = (250 * scale).toInt()
        loadingDialog.window?.setLayout(sizeXY, sizeXY)


    }

    fun serch(text: String) {
        loadingDialog.show()
        apiManager.searchMovies(text, object : API_Manager.apiCallBack<Movie> {
            override fun onSuccess(data: Movie) {
                binding.recyclerView.adapter =
                    Special_Genres_Adpter(data.data, this@Search_Activity)
                binding.recyclerView.layoutManager =
                    GridLayoutManager(this@Search_Activity, 2, RecyclerView.VERTICAL, false)

                loadingDialog.dismiss()
            }

            override fun onError(errorMessage: String) {

            }

        })
    }

    override fun clickOnMovie(film_id: Int) {
        val intent = Intent(this, Info_Movie_Activity::class.java)
        intent.putExtra(keySendMovieID, film_id)
        startActivity(intent)

    }
}