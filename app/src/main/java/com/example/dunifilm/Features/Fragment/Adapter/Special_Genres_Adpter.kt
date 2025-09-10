package com.example.dunifilm.Features.Fragment.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dunifilm.Modle.Movie
import com.example.dunifilm.databinding.ItemRecyclerViewBinding
import com.example.dunifilm.databinding.ItemRecyclerrViewBinding

class Special_Genres_Adpter(val data: List<Movie.Data>) :
    RecyclerView.Adapter<Special_Genres_Adpter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemRecyclerrViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(position: Int) {
            val item = data[position]

            binding.name.text = item.title
            binding.year.text = item.year

            Glide.with(binding.root)
                .load(item.poster)
                .into(binding.imgMovie)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemRecyclerrViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(position)
    }
}