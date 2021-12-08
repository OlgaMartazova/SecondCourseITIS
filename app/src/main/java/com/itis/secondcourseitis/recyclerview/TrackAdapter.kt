package com.itis.secondcourseitis.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itis.secondcourseitis.model.Track

class TrackAdapter(
    private val list: ArrayList<Track>,
    private val action: (Int) -> Unit
): RecyclerView.Adapter<TrackHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int)
    : TrackHolder = TrackHolder.create(parent, action)

    override fun onBindViewHolder(holder: TrackHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}
