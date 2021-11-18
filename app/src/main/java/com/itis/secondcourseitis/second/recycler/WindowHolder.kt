package com.itis.secondcourseitis.second.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.itis.secondcourseitis.databinding.ItemWindowBinding
import com.itis.secondcourseitis.model.Window

class WindowHolder(
    private val binding: ItemWindowBinding,
    private val glide: RequestManager,
    private val clickListener: (item: Window) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var windowItem: Window? = null

    init{
        windowItem?.let { it1 -> clickListener.invoke(it1)}
    }

    fun bind(item: Window) {
        windowItem = item

        with(binding) {
            tvTitle.text = item.title
            tvDesc.text = item.desc

            glide.load(item.url).into(ivImage)
            btnDelete.setOnClickListener {
                clickListener.invoke(item)
            }
        }
    }


    fun updateFields(bundle: Bundle) {
        bundle.run {
            getString("TITLE")?.also {
                updateTitle(it)
            }
            getString("DESC")?.also {
                updateDesc(it)
            }
        }
    }

    fun updateTitle(title: String) {
        binding.tvTitle.text = title
    }

    fun updateDesc(desc: String) {
        binding.tvDesc.text = desc
    }


    companion object {
        fun create(
            parent: ViewGroup,
            glide: RequestManager,
            clickListener: (item: Window) -> Unit
        ) = WindowHolder(
            ItemWindowBinding.inflate(
                LayoutInflater
                    .from(parent.context),
                parent,
                false
            ),
            glide,
            clickListener
        )
    }
}
