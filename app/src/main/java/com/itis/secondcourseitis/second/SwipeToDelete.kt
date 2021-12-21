package com.itis.secondcourseitis.second

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.itis.secondcourseitis.model.WindowRepository.windows
import com.itis.secondcourseitis.second.recycler.WindowListAdapter

class SwipeToDelete (
    var adapter: WindowListAdapter
    ) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val pos = viewHolder.adapterPosition
        adapter.removeItem(windows[pos])
    }
}
