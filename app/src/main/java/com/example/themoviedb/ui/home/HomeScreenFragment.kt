package com.example.themoviedb.ui.home

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.themoviedb.R
import com.example.themoviedb.databinding.FragmentMainScreenBinding
import com.example.themoviedb.util.Status.*

class HomeScreenFragment : Fragment() {
    private lateinit var binding: FragmentMainScreenBinding
    private lateinit var viewModel: HomeScreenViewModel
    private var homeScreenAdapter = HomeScreenAdapter()
    private var page = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_screen, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = HomeViewModelFactory(this)
        viewModel = ViewModelProvider(this, factory).get(HomeScreenViewModel::class.java)

        binding.recyclerView.apply {
            adapter = homeScreenAdapter
            layoutManager = GridLayoutManager(activity, 2)
        }

        initSpinner()
        onSelectSpinnerItem()
        observers()
    }

    private fun observers() {
        viewModel.resource.observe(viewLifecycleOwner, {
            it?.let { resource ->
                when (resource.status) {
                    SUCCESS -> {
                        binding.loadingView.visibility = View.GONE
                        resource.data?.let { movieList ->
                            page = movieList.page
                            homeScreenAdapter.updateItems(movieList)
                        }
                    }
                    ERROR -> {
                        binding.loadingView.visibility = View.GONE
                        Toast.makeText(
                            activity,
                            resource.message ?: resources.getString(R.string.error),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    LOADING -> {
                        binding.loadingView.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun initSpinner() {
        activity?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.categories,
                R.layout.spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinner.adapter = adapter
            }
        }
    }

    private fun onSelectSpinnerItem() {
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                homeScreenAdapter.deleteItems()
                viewModel.filter = binding.spinner.selectedItem.toString()
                viewModel.callMovies()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }
}