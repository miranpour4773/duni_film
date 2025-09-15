package com.example.dunifilm.Features.Fragment.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dunifilm.Features.Fragment.Adapter.Special_Genres_Adpter.ViewHolder
import com.example.dunifilm.Modle.Movie
import com.example.dunifilm.databinding.WatchlistItemBinding

class SearchAdapter (val data : List<Movie.Data> , val workOOnItem: WorkOOnItem) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    inner class SearchViewHolder(val binding: WatchlistItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bindData(position: Int){
            val item = data[position]

            binding.imageView6.visibility = View.INVISIBLE
            binding.time.visibility = View.INVISIBLE
            binding.name.text = item.title
            binding.year.text = item.year
            binding.ratting.text = item.imdbRating
            Glide.with(binding.root)
                .load(item.poster)
                .into(binding.imgPoster)

            binding.main.setOnClickListener {
                workOOnItem.clickOnItem(item.id)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = WatchlistItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bindData(position)
    }

    override fun getItemCount(): Int = data.size


    interface WorkOOnItem{
        fun clickOnItem(filmid:Int)
    }
}