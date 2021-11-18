package com.itis.secondcourseitis.third.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.itis.secondcourseitis.databinding.ItemCvHouseBinding
import com.itis.secondcourseitis.databinding.ItemWindowBinding
import com.itis.secondcourseitis.model.House
import com.itis.secondcourseitis.model.Window
import com.itis.secondcourseitis.second.recycler.WindowHolder

class HouseHolder (
    private val binding: ItemCvHouseBinding,
    private val glide: RequestManager,
) : RecyclerView.ViewHolder(binding.root) {
    private var house: House? = null


    fun bind(item: House) {
        house = item
        with(binding) {
            house?.let {
                tvTitle.text = it.title
                tvDesc.text = it.desc
                vp2Images.adapter = ViewPagerAdapter(it.images, glide)
            }
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            glide: RequestManager,
        ) = HouseHolder(
            ItemCvHouseBinding.inflate(
                LayoutInflater
                    .from(parent.context),
                parent,
                false
            ),
            glide
        )
    }
}
