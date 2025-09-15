package com.example.dunifilm.Features.Fragment.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dunifilm.Modle.Like_Movie
import com.example.dunifilm.databinding.WatchlistItemBinding

class WatchListAdapter (val data: ArrayList<Like_Movie>, val workOnItem: WorkOnItem): RecyclerView.Adapter<WatchListAdapter.WAtchListViewHolder>() {
    inner class WAtchListViewHolder(val binding : WatchlistItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bindData(position: Int){
            val item = data[position]
            binding.name.text = item.film.title
            binding.year.text = item.film.year

            val runtimeStr = item.film.runtime?.replace(",", "")?.trim() ?: "0"
            val time = runtimeStr.filter { it.isDigit() }.toIntOrNull() ?: 0

            val hour = time / 60
            val min = time % 60

            binding.time.text = "$hour hr $min min"

            binding.ratting.text = item.film.imdbRating
            Glide.with(binding.root)
                .load(item.film.poster)
                .into(binding.imgPoster)

            binding.main.setOnClickListener {
                workOnItem.clickOnItem(item.filmId)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WAtchListViewHolder {
        val binding = WatchlistItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return WAtchListViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: WAtchListViewHolder, position: Int) {
        holder.bindData(position)
    }

    interface WorkOnItem{
        fun clickOnItem(filmid:Int)
    }
}