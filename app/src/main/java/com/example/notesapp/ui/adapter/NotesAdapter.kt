package com.example.notesapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.data.local.Item
import com.example.notesapp.databinding.GridListItemBinding

class NotesAdapter: ListAdapter<Item,NotesAdapter.NotesViewHolder>(ItemComparator()) {

    class NotesViewHolder(val binding: GridListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {
            binding.apply {
                notesTitle.text = item.title
                notesBody.text = item.description
            }
        }

        companion object {
            fun create(parent: ViewGroup): NotesViewHolder {
                val binding = GridListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                return NotesViewHolder(binding)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    class ItemComparator: ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)

        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(position)
        }
    }
}