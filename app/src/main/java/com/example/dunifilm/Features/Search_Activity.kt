package com.example.dunifilm.Features

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dunifilm.Features.Fragment.Adapter.HistoryAdapter
import com.example.dunifilm.Features.Fragment.Adapter.SearchAdapter
import com.example.dunifilm.Modle.API_Manager
import com.example.dunifilm.Modle.Movie
import com.example.dunifilm.Modle.keySendMovieID
import com.example.dunifilm.R
import com.example.dunifilm.databinding.ActivitySearchBinding

class Search_Activity : AppCompatActivity(), SearchAdapter.WorkOOnItem {
    lateinit var historyAdapter: HistoryAdapter
    var apiManager = API_Manager()
    lateinit var historySearch: ArrayList<String>
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

        binding.recyclerView.visibility = View.INVISIBLE
        binding.historyRecycler.visibility = View.INVISIBLE

       historySearch = arrayListOf()
       val adapter = HistoryAdapter(historySearch,{ item ->
           // وقتی روی آیتم کلیک شد
           binding.search.setQuery(item, false)  // نمایش متن در SearchView
           serch(item)  // اجرای تابع search
       })
       binding.historyRecycler.adapter = adapter
       binding.historyRecycler.layoutManager =
           LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


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
                serch(query!!)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                  adapter.filter(newText.orEmpty()) // فیلتر کردن لیست بر اساس تایپ کاربر
                binding.historyRecycler.visibility = View.VISIBLE
                binding.NOTFOUND1.visibility = View.INVISIBLE
                binding.NOTFOUND.visibility = View.INVISIBLE
                binding.NOTFOUND2.visibility = View.INVISIBLE
                return true
            }
        })

        val dialogView = layoutInflater.inflate(R.layout.loadinf_dialog, null)
        val builder = AlertDialog.Builder(this)
        builder.setView(dialogView)
        loadingDialog = builder.create()

        loadingDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        loadingDialog.setCancelable(false)
        val scale = resources.displayMetrics.density
        val sizeXY = (250 * scale).toInt()
        loadingDialog.window?.setLayout(sizeXY, sizeXY)


    }

    fun serch(text: String) {
        loadingDialog.show()
        apiManager.searchMovies(text, object : API_Manager.apiCallBack<Movie> {
            override fun onSuccess(data: Movie) {
                if (data.data.isNotEmpty()) {
                    binding.recyclerView.adapter =
                        SearchAdapter(data.data, this@Search_Activity )
                    binding.recyclerView.layoutManager =
                        LinearLayoutManager(this@Search_Activity,  RecyclerView.VERTICAL, false)
                    if (!historySearch.contains(text)) {
                        historySearch.add(0, text) // ابتدای لیست اضافه میشه
                        binding.historyRecycler.adapter?.notifyItemInserted(0)
                        binding.recyclerView.visibility = View.VISIBLE
                    }
                } else {
                    binding.NOTFOUND1.visibility = View.VISIBLE
                    binding.NOTFOUND.visibility = View.VISIBLE
                    binding.NOTFOUND2.visibility = View.VISIBLE
                    if (!historySearch.contains(text)) {
                        historySearch.add(0, text) // ابتدای لیست اضافه میشه
                        binding.historyRecycler.adapter?.notifyItemInserted(0)
                        binding.historyRecycler.visibility = View.VISIBLE
                        // فیلم پیدا نشد
                        binding.recyclerView.visibility = View.INVISIBLE
                        binding.historyRecycler.visibility = View.VISIBLE
                    }

                    loadingDialog.dismiss()
                }
            }

            override fun onError(errorMessage: String) {
//            binding.recyclerView.visibility = View.GONE
//            binding.historyRecycler.visibility = View.VISIBLE
                loadingDialog.dismiss()
            }
        })
    }



    override fun clickOnItem(filmid: Int) {
        val intent = Intent(this, Info_Movie_Activity::class.java)
        intent.putExtra(keySendMovieID, filmid)
        startActivity(intent)    }

}