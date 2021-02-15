package com.example.themoviedb.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.*
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.themoviedb.R
import com.example.themoviedb.databinding.FragmentDetailsScreenBinding

class DetailsScreenFragment : Fragment() {
    private lateinit var binding: FragmentDetailsScreenBinding
    private val imageUrl = "https://image.tmdb.org/t/p/original"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_details_screen, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val poster = arguments?.getString("poster")
        val backdrop = arguments?.getString("backdrop")
        val title = arguments?.getString("title")
        val releaseDate = arguments?.getString("releaseDate")
        val overview = arguments?.getString("overview")
        val ratings = arguments?.getDouble("ratings")

        binding.titleText.text = title
        binding.releaseText.text = releaseDate
        binding.overviewText.text = overview
        binding.ratingTextView.text = ratings.toString()

        initGlide(poster, binding.moviePoster)
        initGlide(backdrop, binding.movieBackdrop)
    }

    private fun initGlide(imageUri: String?, imageView: ImageView) {
        Glide.with(this)
            .load(imageUrl + imageUri)
            .fitCenter()
            .into(imageView)
    }
}