package com.ifs21046.dinopedia

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ListGroupAdapter {
    class ListGroupAdapter(private val listGroup: ArrayList<Group>) :
        RecyclerView.Adapter<ListGroupAdapter.ListViewHolder>() {
        private lateinit var onItemClickCallback: OnItemClickCallback
        fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
            this.onItemClickCallback = onItemClickCallback
        }
        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
            val binding =
                ItemRowSmBinding.inflate(
                    LayoutInflater.from(viewGroup.context),
                    viewGroup, false)
            return ListViewHolder(binding)
        }
        @SuppressLint("SetTextI18n")
        override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
            val group = listGroup[position]
        }
        override fun getItemCount(): Int = listGroup.size
        class ListViewHolder(var binding: ItemRowSmBinding) :
            RecyclerView.ViewHolder(binding.root)
        interface OnItemClickCallback {
            fun onItemClicked(data: Group)
        }
    }
}