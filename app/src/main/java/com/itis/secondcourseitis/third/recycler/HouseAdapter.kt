package com.itis.secondcourseitis.third.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.itis.secondcourseitis.model.House

class HouseAdapter (
    private val list: List<House>,
    private val glide: RequestManager,
) : RecyclerView.Adapter<HouseHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HouseHolder = HouseHolder.create(parent, glide)

    override fun onBindViewHolder(holder: HouseHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}
