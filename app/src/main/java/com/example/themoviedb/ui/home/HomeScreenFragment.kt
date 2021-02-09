package com.example.themoviedb.ui.home

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.themoviedb.R
import com.example.themoviedb.databinding.FragmentMainScreenBinding


class HomeScreenFragment : Fragment() {
    private lateinit var binding: FragmentMainScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_screen, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.apply {
            adapter = HomeScreenAdapter()
            layoutManager = GridLayoutManager(activity, 2)
        }
    }
}