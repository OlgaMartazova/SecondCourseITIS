package com.itis.secondcourseitis.second.diffutil

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import com.itis.secondcourseitis.model.Window

class WindowDiffItemCallback: DiffUtil.ItemCallback<Window>() {
    override fun areItemsTheSame(
        oldItem: Window,
        newItem: Window
    ): Boolean = oldItem.title == newItem.title

    override fun areContentsTheSame(
        oldItem: Window,
        newItem: Window
    ): Boolean = oldItem == newItem

    override fun getChangePayload(oldItem: Window, newItem: Window): Any? {
        val bundle = Bundle()
        if (oldItem.title != newItem.title) {
            bundle.putString("TITLE", newItem.title)
        }
        if (oldItem.desc != newItem.desc) {
            bundle.putString("DESC", newItem.desc)
        }
        if (oldItem.url != newItem.url) {
            bundle.putString("URL", newItem.url)
        }
        if (bundle.isEmpty) return null
        return bundle
    }
}
