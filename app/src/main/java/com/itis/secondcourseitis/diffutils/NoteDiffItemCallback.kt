package com.itis.secondcourseitis.diffutils

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import com.itis.secondcourseitis.ExtConstants.DATE
import com.itis.secondcourseitis.ExtConstants.DESC
import com.itis.secondcourseitis.ExtConstants.TITLE
import com.itis.secondcourseitis.model.Note
import com.itis.secondcourseitis.recycler.DateConverter.convertDate

class NoteDiffItemCallback: DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(
        oldItem: Note,
        newItem: Note
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Note,
        newItem: Note
    ): Boolean = oldItem == newItem

    override fun getChangePayload(
        oldItem: Note,
        newItem: Note
    ): Any? {
        val bundle = Bundle()
        if (oldItem.title != newItem.title) {
            bundle.putString(TITLE, newItem.title)
        }
        if (oldItem.desc != newItem.desc) {
            bundle.putString(DESC, newItem.desc)
        }
        if (oldItem.date != newItem.date) {
            bundle.putString(DATE, convertDate(newItem.date))
        }
        if (bundle.isEmpty) return null
        return bundle
    }

}
