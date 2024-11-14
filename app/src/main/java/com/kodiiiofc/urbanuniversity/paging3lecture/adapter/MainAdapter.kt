package com.kodiiiofc.urbanuniversity.paging3lecture.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kodiiiofc.urbanuniversity.paging3lecture.databinding.ListItemBinding
import com.kodiiiofc.urbanuniversity.paging3lecture.model.Item

class MainAdapter : PagingDataAdapter<Item, MainAdapter.MainViewHolder>(DIFF_CALLBACK) {
    inner class MainViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Item>() {
            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean =
                oldItem.id == newItem.id

            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean =
                oldItem == newItem

        }
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.apply {
            textView.text = item?.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
}