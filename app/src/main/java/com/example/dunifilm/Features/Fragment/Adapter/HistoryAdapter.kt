package com.example.dunifilm.Features.Fragment.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dunifilm.databinding.TxtHistoryBinding

class HistoryAdapter(
    private val originalList: ArrayList<String>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<HistoryAdapter.History_View_Holder>() {

    private val filteredList = mutableListOf<String>().apply { addAll(originalList) }

    inner class History_View_Holder(private val binding: TxtHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.txtHistory.text = item
            binding.root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): History_View_Holder {
        val binding = TxtHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return History_View_Holder(binding)
    }

    override fun getItemCount(): Int = filteredList.size

    override fun onBindViewHolder(holder: History_View_Holder, position: Int) {
        holder.bind(filteredList[position])
    }

    fun filter(query: String) {
        filteredList.clear()
        if(query.isEmpty()) {
            filteredList.addAll(originalList)
        } else {
            filteredList.addAll(originalList.filter { it.contains(query, ignoreCase = true) })
        }
        notifyDataSetChanged()
    }

    fun addIfNotExist(query: String) {
        if(!originalList.contains(query)) {
            originalList.add(0, query) // اضافه به ابتدای لیست
        }
        filter("") // نمایش کل لیست بعد از اضافه شدن
    }
}

