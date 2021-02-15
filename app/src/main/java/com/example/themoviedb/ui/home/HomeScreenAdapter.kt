package com.example.themoviedb.ui.home

import android.content.Context
import android.content.res.Resources
import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themoviedb.data.model.MovieDetails
import com.example.themoviedb.data.model.MovieList
import com.example.themoviedb.databinding.ItemListBinding

class HomeScreenAdapter : RecyclerView.Adapter<HomeScreenAdapter.ViewHolder>() {
    private lateinit var context: Context
    private lateinit var _movieList: MovieList
    private var _imagesArray = ArrayList<String>()

    companion object {
        var itemClicked: ((movieDetails: MovieDetails) -> Unit)? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(_imagesArray[position], _movieList.results[position], context)
    }

    override fun getItemCount(): Int {
        return _imagesArray.size
    }

    fun updateItems(movieList: MovieList) {
        _movieList = movieList
        _movieList.results.forEach {
            _imagesArray.add(it.poster_path)
        }
        notifyDataSetChanged()
    }

    fun deleteItems() {
        _imagesArray = arrayListOf()
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(image: String, movieDetails: MovieDetails, context: Context) {
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
                itemClicked?.invoke(movieDetails)
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