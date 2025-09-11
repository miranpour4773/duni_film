package com.example.dunifilm.Features.Fragment.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dunifilm.Modle.Movie
import com.example.dunifilm.databinding.ItemRecyclerViewBinding

class Recycler_Adpter(val data: List<Movie.Data> , val senddata: sendData) :
    RecyclerView.Adapter<Recycler_Adpter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(position: Int) {
            val item = data[position]

            binding.name.text = item.title
            binding.year.text = item.year

            Glide.with(binding.root)
                .load(item.poster)
                .into(binding.imgMovie)

            binding.imgMovie.setOnClickListener {
                senddata.clickOnMovie(item.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = 5

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(position)
    }

    interface sendData {
        fun clickOnMovie(film_id: Int)
    }
}