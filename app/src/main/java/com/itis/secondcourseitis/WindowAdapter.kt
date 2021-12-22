package com.itis.secondcourseitis

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager

class WindowAdapter(
    private val list: ArrayList<Window>,
    private val glide: RequestManager,
    private val action: (Int)-> Unit
) : RecyclerView.Adapter<WindowHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int)
     : WindowHolder = WindowHolder.create(parent, glide, action)

    override fun onBindViewHolder(holder: WindowHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}
