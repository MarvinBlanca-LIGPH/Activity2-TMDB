package com.example.themoviedb.ui.home

import android.content.Context
import android.content.res.Resources
import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themoviedb.data.model.*
import com.example.themoviedb.databinding.ItemListBinding

class HomeScreenAdapter : RecyclerView.Adapter<HomeScreenAdapter.ViewHolder>() {
    private lateinit var context: Context
    private var movieDetails = arrayListOf<MovieDetails>()
    private var imagesArray = arrayListOf<String>()

    companion object {
        var itemClicked: ((movieDetails: MovieDetails) -> Unit)? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(imagesArray[position], movieDetails, position, context)
    }

    override fun getItemCount(): Int {
        return imagesArray.size
    }

    fun updateItems(movieList: MovieList) {
        movieList.results.forEach { details ->
            movieDetails.add(details)
            imagesArray.add(details.poster_path)
        }
        notifyDataSetChanged()
    }

    fun deleteItems() {
        movieDetails = arrayListOf()
        imagesArray = arrayListOf()
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            image: String,
            movieDetails: ArrayList<MovieDetails>,
            position: Int,
            context: Context
        ) {
            val posterImage = "https://image.tmdb.org/t/p/original$image"

            binding.imageProgress.visibility = View.VISIBLE
            Glide.with(context)
                .load(posterImage)
                .override(
                    Resources.getSystem().displayMetrics.widthPixels / 2,
                    Resources.getSystem().displayMetrics.heightPixels / 2
                )
                .fitCenter()
                .into(binding.movieImage)
            binding.imageProgress.visibility = View.GONE

            binding.cardView.setOnClickListener {
                itemClicked?.invoke(movieDetails[position])
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemListBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}