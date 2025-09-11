package com.example.dunifilm.Features.Fragment.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.dunifilm.Modle.Movie_Info
import com.example.dunifilm.databinding.ItemRecyclerViewActorsBinding

class Recycler_Actors(val data :List<String>):RecyclerView.Adapter<Recycler_Actors.Actors_View_Holder>() {

    inner class Actors_View_Holder(val binding:ItemRecyclerViewActorsBinding):RecyclerView.ViewHolder(binding.root){
        fun bindData(position: Int){
            val item = data[position]
            Glide.with(binding.root)
                .load(item)
                .circleCrop()
                .override(Target.SIZE_ORIGINAL,Target.SIZE_ORIGINAL)
                .into(binding.imgActor)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Actors_View_Holder {
        val binding = ItemRecyclerViewActorsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Actors_View_Holder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: Actors_View_Holder, position: Int) {
        holder.bindData(position)
    }
}