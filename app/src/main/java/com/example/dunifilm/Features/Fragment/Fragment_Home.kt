package com.example.dunifilm.Features.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dunifilm.Features.Fragment.Adapter.Crime_Recycler_Adpter
import com.example.dunifilm.Modle.API_Manager
import com.example.dunifilm.Modle.Genres
import com.example.dunifilm.Modle.Movie
import com.example.dunifilm.R
import com.example.dunifilm.databinding.HomeFragmentBinding
import com.google.android.material.chip.Chip

class Fragment_Home : Fragment() {
    val api_manager = API_Manager()
    lateinit var binding: HomeFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initUi()
        binding = HomeFragmentBinding.inflate(layoutInflater)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initUi()
    }


    private fun initUi() {

        api_manager.getGenres(object : API_Manager.apiCallBack<List<Genres>> {
            override fun onSuccess(data: List<Genres>) {
                createChips(data)
            }

            override fun onError(errorMessage: String) {
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
            }

        })


        dateCrime()
    }

    private fun dateCrime() {
        // شناسه ژانر (genreId) را برای دریافت فیلم‌های مورد نظر مشخص کنید
        // در اینجا به عنوان مثال از ID=1 استفاده شده است
        api_manager.getMovies(1, object : API_Manager.apiCallBack<Movie> {
            override fun onSuccess(data: Movie) {
                // 'data.data' حاوی لیست فیلم‌ها است
                binding.recyclerCrime.adapter = Crime_Recycler_Adpter(data.data)
                binding.recyclerCrime.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL , false)

            }

            override fun onError(errorMessage: String) {
                Toast.makeText(requireContext(), "خطا در دریافت فیلم‌ها: $errorMessage", Toast.LENGTH_SHORT).show()
            }
        })
    }


    private fun createChips(data: List<Genres>) {
        data.forEach {
            val chip = LayoutInflater.from(requireContext())
                .inflate(R.layout.chip_category, binding.chipGroupCategories, false) as Chip
            chip.id = it.id
            chip.text = it.name
            binding.chipGroupCategories.addView(chip)
        }
    }
}
