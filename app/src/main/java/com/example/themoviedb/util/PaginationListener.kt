package com.example.themoviedb.util

import androidx.recyclerview.widget.*

abstract class PaginationListener(
    private val layoutManager: GridLayoutManager,
    private val pageSize: Int
) : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstItemVisiblePosition = layoutManager.findFirstVisibleItemPosition()

        if (!isLastPage() && !isLoading()) {
            if ((visibleItemCount + firstItemVisiblePosition) >= totalItemCount
                && firstItemVisiblePosition >= 0
                && totalItemCount >= pageSize
            ) {
                loadMore()
            }
        }
    }

    abstract fun loadMore()
    abstract fun isLastPage(): Boolean
    abstract fun isLoading(): Boolean
}