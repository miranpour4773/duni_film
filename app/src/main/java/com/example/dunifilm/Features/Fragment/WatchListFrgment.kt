package com.example.dunifilm.Features.Fragment

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dunifilm.Features.Fragment.Adapter.WatchListAdapter
import com.example.dunifilm.Features.Info_Movie_Activity
import com.example.dunifilm.Features.like
import com.example.dunifilm.Modle.Like_Movie
import com.example.dunifilm.Modle.keySendMovieID
import com.example.dunifilm.R
import com.example.dunifilm.databinding.WatchlistFragmentBinding

class WatchListFrgment : Fragment(), WatchListAdapter.WorkOnItem {
    lateinit var arrayList: ArrayList<Like_Movie>
    lateinit var binding: WatchlistFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        binding = WatchlistFragmentBinding.inflate(layoutInflater)
        arrayList = like.watchList
        binding.imgBack.setColorFilter(ContextCompat.getColor( requireContext() , R.color.white))
        checkSomething()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        checkData()
        binding.imgBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun clickOnItem(filmid: Int) {
        val intent = Intent(requireContext(), Info_Movie_Activity::class.java)
        intent.putExtra(keySendMovieID, filmid)
        startActivity(intent)
    }


    private fun checkSomething() {
        var adapter = WatchListAdapter(arrayList, this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

    }


    private fun checkData(){
        if( like.watchList != arrayList){
            arrayList = like.watchList
        }
    }




}