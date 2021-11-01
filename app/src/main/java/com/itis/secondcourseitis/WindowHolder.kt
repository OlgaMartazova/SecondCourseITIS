package com.itis.secondcourseitis

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.annotation.GlideModule
import com.itis.secondcourseitis.databinding.ItemWindowBinding

class WindowHolder(
    private val binding: ItemWindowBinding,
    private val glide: RequestManager,
    private val action: (Int)-> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var windowItem: Window? = null

    //one clickListener for each item
    init{
        itemView.setOnClickListener{
            windowItem?.id?.also(action)
        }
    }

    fun bind(item: Window) {
        windowItem = item

        with(binding) {
            tvTitle.text = item.title

            glide.load(item.url).into(ivImage)
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            glide: RequestManager,
            action: (Int)-> Unit
        ) = WindowHolder(
            ItemWindowBinding.inflate(
            LayoutInflater
                .from(parent.context),
                parent,
                false
            ),
            glide,
            action
        )
    }
}
