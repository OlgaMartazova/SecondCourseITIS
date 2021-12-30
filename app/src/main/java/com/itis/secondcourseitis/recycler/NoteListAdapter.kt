package com.itis.secondcourseitis.recycler

import android.os.Bundle
import android.view.ViewGroup
import com.itis.secondcourseitis.model.Note
import androidx.recyclerview.widget.ListAdapter
import com.itis.secondcourseitis.diffutils.NoteDiffItemCallback

class NoteListAdapter(
    private val delete: (Int) -> Unit,
    private val clickOnItem: (Int) -> Unit
) : ListAdapter<Note, NoteViewHolder>(NoteDiffItemCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoteViewHolder = NoteViewHolder.create(parent, delete, clickOnItem)

    override fun onBindViewHolder(
        holder: NoteViewHolder,
        position: Int,
    ) {
        holder.bind(getItem(position))
    }

    override fun onBindViewHolder(
        holder: NoteViewHolder,
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

    override fun submitList(list: MutableList<Note>?) {
        super.submitList(if (list == null) null else ArrayList(list))
    }
}
