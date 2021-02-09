package com.example.themoviedb.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.*
import com.example.themoviedb.R

class DetailsScreenFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details_screen, container, false)
    }
}