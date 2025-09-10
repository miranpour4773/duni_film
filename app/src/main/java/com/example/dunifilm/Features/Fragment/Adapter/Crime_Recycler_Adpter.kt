package com.example.dunifilm.Features.Fragment.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dunifilm.Modle.Movie
import com.example.dunifilm.databinding.ItemRecyclerViewBinding

class Crime_Recycler_Adpter(val data: List<Movie.Data>) :
    RecyclerView.Adapter<Crime_Recycler_Adpter.CrimeViewHolder>() {

    inner class CrimeViewHolder(val binding: ItemRecyclerViewBinding) :
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeViewHolder {
        val binding =
            ItemRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CrimeViewHolder(binding)
    }

    override fun getItemCount(): Int = 5

    override fun onBindViewHolder(holder: CrimeViewHolder, position: Int) {
        holder.bindData(position)
    }
}