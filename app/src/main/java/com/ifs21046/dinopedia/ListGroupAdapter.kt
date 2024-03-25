package com.ifs21046.dinopedia

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifs21046.dinopedia.databinding.ItemRowDinoBinding

class ListGroupAdapter(private val listGroup: ArrayList<Group>) :
    RecyclerView.Adapter<ListGroupAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowDinoBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup, false
        )
        return ListViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val group = listGroup[position]
        holder.binding.dinosaurusgroup.setImageResource(group.icon)
        holder.binding.groupdinosaurus.text = group.name
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listGroup[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listGroup.size

    class ListViewHolder(val binding: ItemRowDinoBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: Group)
    }
}
