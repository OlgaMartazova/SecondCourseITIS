package com.itis.secondcourseitis.second.recycler

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.RequestManager
import com.itis.secondcourseitis.second.diffutil.WindowDiffItemCallback
import com.itis.secondcourseitis.model.Window
import com.itis.secondcourseitis.model.WindowRepository

class WindowListAdapter(
    private val glide: RequestManager,
    private val action: (item: Window) -> Unit
) : ListAdapter<Window, WindowHolder>(WindowDiffItemCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WindowHolder = WindowHolder.create(parent, glide, action)

    override fun onBindViewHolder(
        holder: WindowHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    override fun onBindViewHolder(
        holder: WindowHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            payloads.last().takeIf { it is Bundle }?.let {
                holder.updateFields(it as Bundle)
            }
        }
    }

    override fun submitList(list: MutableList<Window>?) {
        super.submitList(if (list == null) null else ArrayList(list))
    }

    fun removeItem(item: Window) {
        WindowRepository.windows.remove(item)
        submitList(WindowRepository.windows)
    }
}
