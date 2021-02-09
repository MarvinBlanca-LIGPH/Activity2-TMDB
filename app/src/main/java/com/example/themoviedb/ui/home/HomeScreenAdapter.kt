package com.example.themoviedb.ui.home

import android.content.Context
import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedb.databinding.ItemListBinding

class HomeScreenAdapter : RecyclerView.Adapter<HomeScreenAdapter.ViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        for (i in 1..16) {
            holder.bind()
        }
    }

    override fun getItemCount(): Int {
        return 16
    }

    class ViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {}

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemListBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}