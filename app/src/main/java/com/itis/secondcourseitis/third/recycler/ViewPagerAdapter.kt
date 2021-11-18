package com.itis.secondcourseitis.third.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.itis.secondcourseitis.databinding.ItemCvHouseBinding
import com.itis.secondcourseitis.databinding.ItemViewPagerBinding
import com.itis.secondcourseitis.model.House
import com.itis.secondcourseitis.model.HouseRepository

class ViewPagerAdapter(
    private val images: List<String>,
    private val glide: RequestManager,
) : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>(){


    inner class ViewPagerViewHolder(
        private val binding: ItemViewPagerBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(pos: Int) {
            with(binding) {
                glide.load(images[pos]).into(ivImage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        return ViewPagerViewHolder(ItemViewPagerBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount() = images.size
}
